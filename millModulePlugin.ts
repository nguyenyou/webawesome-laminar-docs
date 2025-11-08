import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Root } from "mdast";
import { join, relative } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync, readFileSync } from "fs";
import {
  normalizePath,
  extractPrefixFromDocPath,
  extractHierarchicalPathSegments,
  joinHierarchicalPath,
  type ExampleInfo,
  type ExamplesJson,
  type TemplateContext,
} from "./previewUtils";

/**
 * Convert hyphenated string to camelCase
 * e.g., "zoomable-frame" -> "zoomableFrame"
 * e.g., "button-group-item" -> "buttonGroupItem"
 * Preserves strings without hyphens unchanged
 */
const toCamelCase = (str: string): string => {
  if (!str.includes("-")) {
    return str;
  }
  const parts = str.split("-");
  return parts[0] + parts.slice(1).map(part => 
    part.charAt(0).toUpperCase() + part.slice(1)
  ).join("");
};

/**
 * Convert an array of path segments to camelCase and join them with "/"
 * e.g., ["webawesome", "button-group"] -> "webawesome/buttonGroup"
 */
const joinHierarchicalPathCamelCase = (segments: string[]): string => {
  return segments.map(toCamelCase).join("/");
};

/**
 * Generate mill package name for a hierarchical example module
 * e.g., ["webawesome", "button"], 1 -> 'build.examples.webawesome.button.example1'
 * Converts hyphenated segments to camelCase for valid Scala package names
 */
const getMillPackageName = (pathSegments: string[], counter: number): string => {
  const exampleName = `example${counter}`;
  const packageSegments = pathSegments.map(toCamelCase);
  const packagePath = packageSegments.join(".");
  return `build.examples.${packagePath}.${exampleName}`;
};

/**
 * Indent code by adding spaces to each line
 */
const indentCode = (code: string | null | undefined, spaces: number = 4): string => {
  if (!code) return "";
  const indent = " ".repeat(spaces);
  return code.split("\n").map(line => `${indent}${line}`).join("\n");
};

/**
 * Extract template type from code block meta
 * Returns "preview" by default, or "examples" if specified
 */
const extractTemplateType = (meta: string | null | undefined): "preview" | "examples" => {
  if (!meta) {
    return "preview";
  }
  if (meta.includes("examples")) {
    return "examples";
  }
  return "preview";
};

/**
 * Generate package.mill content for a Mill module
 */
const createPackageMillContent = (packageName: string): string => {
  return `package ${packageName}\n\nobject \`package\` extends build.ExampleModule\n`;
};

export const applyTemplate = (ctx: TemplateContext): string => {
  // ctx.prefix is now the hierarchical path joined with "/" (using camelCase segments)
  // Convert path segments to camelCase package names for valid Scala package names
  const pathSegments = ctx.prefix.split("/");
  const packageSegments = pathSegments.map(toCamelCase);
  const packageName = `examples.${packageSegments.join(".")}.example${ctx.counter}`;
  const userCode = ctx.userCode || "";
  
  return `package ${packageName}
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
${indentCode(userCode, 6)}
  })
}
  `;
};

/**
 * Parse Scala code to identify complete top-level expressions
 * by tracking parentheses depth. Only splits at top-level boundaries,
 * preserving nested structures.
 */
export const parseTopLevelExpressions = (code: string): string[] => {
  if (!code.trim()) {
    return [];
  }

  const expressions: string[] = [];
  let currentExpression = "";
  let depth = 0;
  let i = 0;
  let inString = false;
  let stringChar: string | null = null;
  let inSingleLineComment = false;
  let inMultiLineComment = false;

  while (i < code.length) {
    const char = code[i];
    const prevChar = i > 0 ? code[i - 1] : null;
    const nextChar = i < code.length - 1 ? code[i + 1] : null;

    // Handle string literals
    if (!inSingleLineComment && !inMultiLineComment) {
      if (char === '"' || char === "'" || char === '`') {
        // Check if this quote is escaped by counting backslashes
        let backslashCount = 0;
        let j = i - 1;
        while (j >= 0 && code[j] === '\\') {
          backslashCount++;
          j--;
        }
        // If even number of backslashes (or zero), the quote is not escaped
        const isEscaped = backslashCount % 2 === 1;
        
        if (!isEscaped) {
          if (!inString) {
            inString = true;
            stringChar = char;
          } else if (char === stringChar) {
            inString = false;
            stringChar = null;
          }
        }
        currentExpression += char;
        i++;
        continue;
      }
    }

    // Handle comments
    if (!inString) {
      if (char === '/' && nextChar === '/') {
        inSingleLineComment = true;
        currentExpression += char;
        i++;
        continue;
      }
      if (inSingleLineComment && char === '\n') {
        inSingleLineComment = false;
        currentExpression += char;
        i++;
        continue;
      }
      if (char === '/' && nextChar === '*') {
        inMultiLineComment = true;
        currentExpression += char;
        i++;
        continue;
      }
      if (inMultiLineComment && char === '*' && nextChar === '/') {
        inMultiLineComment = false;
        currentExpression += char;
        if (nextChar) {
          currentExpression += nextChar;
        }
        i += 2;
        continue;
      }
    }

    // Skip processing inside comments or strings
    if (inString || inSingleLineComment || inMultiLineComment) {
      currentExpression += char;
      i++;
      continue;
    }

    // Track parentheses depth
    if (char === '(') {
      depth++;
      currentExpression += char;
    } else if (char === ')') {
      depth--;
      currentExpression += char;
      
      // When depth returns to 0, we have a complete top-level expression
      if (depth === 0) {
        // Check if this is followed by another opening paren (curried call)
        // by looking ahead past whitespace
        let lookAhead = i + 1;
        while (lookAhead < code.length && /\s/.test(code[lookAhead])) {
          lookAhead++;
        }
        const isCurriedCall = lookAhead < code.length && code[lookAhead] === '(';
        
        // Only split if it's not a curried call
        if (!isCurriedCall) {
          // Preserve the expression as-is (including internal whitespace/newlines)
          // Only trim if it's completely empty
          if (currentExpression.trim().length > 0) {
            expressions.push(currentExpression);
          }
          currentExpression = "";
          // Skip whitespace after the expression
          i++;
          while (i < code.length && /\s/.test(code[i])) {
            i++;
          }
          continue;
        }
        // If it's a curried call, continue building the expression
      }
    } else {
      currentExpression += char;
    }

    i++;
  }

  // Handle any remaining expression (in case of unclosed parentheses or incomplete code)
  if (currentExpression.trim().length > 0) {
    expressions.push(currentExpression);
  }

  return expressions;
};

/**
 * Group expressions by blank lines (2+ consecutive newlines).
 * Returns an array of groups, where each group is an array of expressions.
 */
const groupExpressionsByBlankLines = (code: string, expressions: string[]): string[][] => {
  if (expressions.length === 0) {
    return [];
  }

  const groups: string[][] = [];
  let currentGroup: string[] = [];
  let prevExprEnd = 0;

  for (let i = 0; i < expressions.length; i++) {
    const expr = expressions[i];
    
    // Find the expression in the code starting from where the previous expression ended
    const exprStart = code.indexOf(expr, prevExprEnd);
    if (exprStart === -1) {
      // If we can't find it, just add to current group
      currentGroup.push(expr);
      continue;
    }

    // Check if there's a blank line (2+ consecutive newlines) between previous and current expression
    if (i > 0) {
      const betweenExpressions = code.substring(prevExprEnd, exprStart);
      // Check if there are 2+ consecutive newlines (blank line)
      const hasBlankLine = /\n\s*\n/.test(betweenExpressions);
      
      if (hasBlankLine) {
        // Start a new group
        if (currentGroup.length > 0) {
          groups.push(currentGroup);
        }
        currentGroup = [expr];
      } else {
        // Continue current group
        currentGroup.push(expr);
      }
    } else {
      // First expression, start first group
      currentGroup.push(expr);
    }

    prevExprEnd = exprStart + expr.length;
  }

  // Add the last group
  if (currentGroup.length > 0) {
    groups.push(currentGroup);
  }

  return groups.length > 0 ? groups : [expressions];
};

/**
 * Apply examples template: converts newline-separated code into comma-separated arguments
 * wrapped in Examples(...) calls, grouped by blank lines, and wrapped in ExampleGroups(...).
 * Only adds commas between complete top-level expressions, preserving nested structures.
 */
export const applyExamplesTemplate = (ctx: TemplateContext): string => {
  // ctx.prefix is now the hierarchical path joined with "/" (using camelCase segments)
  // Convert path segments to camelCase package names for valid Scala package names
  const pathSegments = ctx.prefix.split("/");
  const packageSegments = pathSegments.map(toCamelCase);
  const packageName = `examples.${packageSegments.join(".")}.example${ctx.counter}`;
  const userCode = ctx.userCode || "";
  
  // Parse into top-level expressions (preserving nested structure)
  const expressions = parseTopLevelExpressions(userCode);
  
  // Group expressions by blank lines
  const groups = groupExpressionsByBlankLines(userCode, expressions);
  
  // Create Examples() call for each group
  const examplesCalls = groups.map(group => {
    // Join expressions in group with commas, preserving their internal structure
    const examplesArgs = group
      .map(expr => expr.endsWith(",") ? expr : `${expr},`)
      .join("\n");
    
    return `Examples(
${indentCode(examplesArgs, 2)}
)`;
  });
  
  // Wrap all Examples() calls in ExampleGroups()
  const exampleGroupsArgs = examplesCalls
    .map((call, index) => {
      // Add comma after each call except the last one
      return index < examplesCalls.length - 1 ? `${call},` : call;
    })
    .join("\n");
  
  const exampleGroupsCall = `ExampleGroups(
${indentCode(exampleGroupsArgs, 2)}
)`;
  
  return `package ${packageName}
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
${indentCode(exampleGroupsCall, 6)}
  })
}
  `;
};

/**
 * Apply template based on template type
 * Switches between different template implementations
 */
const applyTemplateByType = (
  templateType: "preview" | "examples",
  ctx: TemplateContext
): string => {
  switch (templateType) {
    case "examples":
      return applyExamplesTemplate(ctx);
    case "preview":
    default:
      return applyTemplate(ctx);
  }
};

/**
 * Ensure parent modules exist for hierarchical structure
 * Creates nested directory structure and package.mill files at each level
 * Uses camelCase for both directory names and package names (per Mill convention)
 * e.g., examples/webawesome/buttonGroup/package.mill
 */
const ensureParentModules = (workspaceRoot: string, pathSegments: string[]): void => {
  const examplesPath = join(workspaceRoot, "examples");
  
  // Ensure root examples/package.mill exists
  const rootPackagePath = join(examplesPath, "package.mill");
  if (!existsSync(rootPackagePath)) {
    mkdirSync(examplesPath, { recursive: true });
    writeFileSync(rootPackagePath, createPackageMillContent("build.examples"));
  }
  
  // Create nested directory structure and package.mill files for each level
  let currentPath = examplesPath;
  let packageName = "build.examples";
  
  for (const segment of pathSegments) {
    // Convert segment to camelCase for directory name (matches package name per Mill convention)
    const camelCaseSegment = toCamelCase(segment);
    currentPath = join(currentPath, camelCaseSegment);
    // Use camelCase for package name (same as directory name)
    packageName = `${packageName}.${camelCaseSegment}`;
    
    // Ensure directory exists
    mkdirSync(currentPath, { recursive: true });
    
    // Create package.mill if it doesn't exist
    const packageMillPath = join(currentPath, "package.mill");
    if (!existsSync(packageMillPath)) {
      writeFileSync(packageMillPath, createPackageMillContent(packageName));
    }
  }
};

/**
 * Generate an example module with package.mill and src/Main.scala
 * Uses hierarchical directory structure with camelCase: examples/{category}/{component}/example{number}/
 * Directory names match package names per Mill convention
 */
const generateExampleModule = (
  pathSegments: string[],
  counter: number,
  scalaCode: string,
  workspaceRoot: string,
  templateType: "preview" | "examples" = "preview"
): void => {
  // Ensure parent modules exist
  ensureParentModules(workspaceRoot, pathSegments);
  
  // Create hierarchical path: examples/webawesome/button/example1
  // Use camelCase segments for directory names (matches package names per Mill convention)
  const exampleName = `example${counter}`;
  const hierarchicalPath = joinHierarchicalPathCamelCase(pathSegments);
  const exampleDir = join(workspaceRoot, "examples", hierarchicalPath, exampleName);
  const srcDir = join(exampleDir, "src");
  const mainScalaPath = join(srcDir, "Main.scala");
  const packageMillPath = join(exampleDir, "package.mill");
  
  // Ensure directories exist
  mkdirSync(srcDir, { recursive: true });
  
  // Generate Main.scala
  // TemplateContext.prefix should be the hierarchical path joined with "/" (using camelCase segments)
  // Package names will be converted to camelCase in applyTemplate/applyExamplesTemplate
  const templateContext: TemplateContext = {
    prefix: hierarchicalPath,
    counter: counter,
    userCode: scalaCode,
  };
  const scalaSource = applyTemplateByType(templateType, templateContext);
  writeFileSync(mainScalaPath, scalaSource);
  
  // Generate package.mill
  const packageName = getMillPackageName(pathSegments, counter);
  writeFileSync(packageMillPath, createPackageMillContent(packageName));
};

/**
 * Recursively clean up example modules that no longer exist
 * Handles both old flat structure and new hierarchical structure
 */
const cleanupOldExamplesRecursive = (
  currentCounters: Set<number>,
  docFilePath: string,
  examplesJson: ExamplesJson,
  currentPath: string,
  workspaceRoot: string
): void => {
  if (!existsSync(currentPath)) {
    return;
  }
  
  try {
    const entries = readdirSync(currentPath, { withFileTypes: true });
    
    for (const entry of entries) {
      const entryPath = join(currentPath, entry.name);
      
      if (entry.isDirectory()) {
        // Check if this is a new counter-based directory (format: "example{N}")
        if (entry.name.startsWith("example") && /^example\d+$/.test(entry.name)) {
          const counterMatch = entry.name.match(/^example(\d+)$/);
          if (counterMatch) {
            const entryCounter = parseInt(counterMatch[1], 10);
            
            // Check if this counter belongs to the current doc file
            const examplesForCurrentDoc = examplesJson.examples.filter(
              ex => ex.docPath === docFilePath && ex.counter === entryCounter
            );
            
            // Remove if it doesn't exist in current counters
            if (examplesForCurrentDoc.length > 0 && !currentCounters.has(entryCounter)) {
              rmSync(entryPath, { recursive: true, force: true });
              continue;
            }
          }
        }
        // Check if this is an old hash-based directory (format: "h{hash}")
        else if (entry.name.startsWith("h") && entry.name.length > 1) {
          // Old hash-based structure: remove it
          const examplesForCurrentDoc = examplesJson.examples.filter(
            ex => ex.docPath === docFilePath && ex.path.includes(entry.name)
          );
          if (examplesForCurrentDoc.length > 0) {
            rmSync(entryPath, { recursive: true, force: true });
            continue;
          }
        }
        // Check if this is an old flat structure directory (format: "{prefix}_{hash}" or "hash_{hash}")
        else if (entry.name.includes("_") && !entry.name.startsWith("example")) {
          // Old flat structure: remove it
          const examplesForCurrentDoc = examplesJson.examples.filter(
            ex => ex.docPath === docFilePath && ex.path.includes(entry.name)
          );
          if (examplesForCurrentDoc.length > 0) {
            rmSync(entryPath, { recursive: true, force: true });
            continue;
          }
        }
        // Check if this is an old "hash_" directory
        else if (entry.name.startsWith("hash_")) {
          const examplesForCurrentDoc = examplesJson.examples.filter(
            ex => ex.docPath === docFilePath && ex.path.includes(entry.name)
          );
          if (examplesForCurrentDoc.length > 0) {
            rmSync(entryPath, { recursive: true, force: true });
            continue;
          }
        }
        // Otherwise, recursively process subdirectories (for hierarchical structure)
        else {
          cleanupOldExamplesRecursive(
            currentCounters,
            docFilePath,
            examplesJson,
            entryPath,
            workspaceRoot
          );
          
          // After recursive cleanup, check if directory is empty and remove it if so
          // (but keep package.mill files)
          try {
            const remainingEntries = readdirSync(entryPath);
            const hasOnlyPackageMill = remainingEntries.length === 1 && remainingEntries[0] === "package.mill";
            if (remainingEntries.length === 0 || hasOnlyPackageMill) {
              // Check if this directory has examples that belong to current doc
              const relativePath = normalizePath(relative(workspaceRoot, entryPath));
              const hasExamplesForDoc = examplesJson.examples.some(
                ex => ex.docPath === docFilePath && ex.path.startsWith(relativePath)
              );
              if (!hasExamplesForDoc) {
                rmSync(entryPath, { recursive: true, force: true });
              }
            }
          } catch {
            // Ignore errors when checking/removing directories
          }
        }
      }
    }
  } catch (error) {
    // Ignore errors during cleanup
    console.warn(`Failed to cleanup old examples in ${currentPath}:`, error);
  }
};

/**
 * Clean up example modules that no longer exist in the current MDX file
 * Works with hierarchical examples/ directory structure
 * Only removes examples that belong to the current doc file
 * Handles migration from old flat structure
 */
const cleanupOldExamples = (
  currentPathCounters: Array<{ pathSegments: string[]; counter: number }>,
  docFilePath: string,
  workspaceRoot: string,
  examplesJson: ExamplesJson
): void => {
  const examplesPath = join(workspaceRoot, "examples");
  
  if (!existsSync(examplesPath)) {
    return;
  }
  
  // Create a set of current counters for quick lookup
  const currentCounters = new Set(currentPathCounters.map(({ counter }) => counter));
  
  // Recursively clean up old examples
  cleanupOldExamplesRecursive(
    currentCounters,
    docFilePath,
    examplesJson,
    examplesPath,
    workspaceRoot
  );
}

/**
 * Get mill build out path for an example
 * e.g., out/examples/webawesome/button/example1/fullLinkJS.dest/main.js
 */
const getMillBuildOutPath = (
  pathSegments: string[],
  counter: number,
  workspaceRoot: string
): string => {
  const exampleName = `example${counter}`;
  // Use camelCase segments for directory paths (matches package names per Mill convention)
  const hierarchicalPath = joinHierarchicalPathCamelCase(pathSegments);
  const pathParts = ["out", "examples", hierarchicalPath, exampleName, "fullLinkJS.dest", "main.js"];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example directory path relative to workspace root
 * e.g., examples/webawesome/button/example1
 */
const getExampleDirectoryPath = (
  pathSegments: string[],
  counter: number,
  workspaceRoot: string
): string => {
  const exampleName = `example${counter}`;
  // Use camelCase segments for directory paths (matches package names per Mill convention)
  const hierarchicalPath = joinHierarchicalPathCamelCase(pathSegments);
  const pathParts = ["examples", hierarchicalPath, exampleName];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example builds path relative to workspace root
 * Uses flat structure for builds: examples-build/webawesome_button_example1.js
 * Converts path segments to camelCase before joining with underscores
 */
const getExampleBuildsPath = (
  pathSegments: string[],
  counter: number,
  workspaceRoot: string
): string => {
  // Convert segments to camelCase and join with underscores for build file name
  const camelCaseSegments = pathSegments.map(toCamelCase);
  const flattenedPrefix = camelCaseSegments.join("_");
  const pathParts = ["examples-build", `${flattenedPrefix}_example${counter}.js`];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Read existing examples.json file
 */
const readExamplesJson = (workspaceRoot: string): ExamplesJson => {
  const examplesJsonPath = join(workspaceRoot, "examples.json");
  
  if (!existsSync(examplesJsonPath)) {
    return { examples: [] };
  }
  
  try {
    const content = readFileSync(examplesJsonPath, "utf-8");
    const parsed = JSON.parse(content) as ExamplesJson;
    // Handle migration from old nested structure
    if (!parsed.examples || !Array.isArray(parsed.examples)) {
      return { examples: [] };
    }
    return parsed;
  } catch (error) {
    console.warn(`Failed to read examples.json:`, error);
    return { examples: [] };
  }
};

/**
 * Write examples.json file
 */
const writeExamplesJson = (workspaceRoot: string, data: ExamplesJson): void => {
  const examplesJsonPath = join(workspaceRoot, "examples.json");
  
  try {
    const content = JSON.stringify(data, null, 2);
    writeFileSync(examplesJsonPath, content, "utf-8");
  } catch (error) {
    console.error(`Failed to write examples.json:`, error);
  }
};

interface MillModulePluginOptions {}

export const millModulePlugin: Plugin<[MillModulePluginOptions?], Root> = () => {
  return (tree, file) => {
    const filePath = file.path || file.history?.[0];
    if (!filePath) {
      console.warn("No file path available for mill module plugin");
      return;
    }

    // Use file.cwd as the workspace root (current working directory)
    const workspaceRoot = file.cwd || process.cwd();

    // Load existing examples.json
    const examplesJson = readExamplesJson(workspaceRoot);

    // Get docs file path relative to workspace root
    const docsFilePath = normalizePath(relative(workspaceRoot, filePath));
    
    // Extract hierarchical path segments from doc file path
    const pathSegments = extractHierarchicalPathSegments(docsFilePath);

    // Track example path segments/counter combinations
    const examplePathCounters: Array<{ pathSegments: string[]; counter: number }> = [];
    const exampleInfos: ExampleInfo[] = [];
    const currentTimestamp = new Date().toISOString();
    
    // Counter for examples in this MDX file (starts at 1)
    let exampleCounter = 1;
    
    visit(tree, "code", (node) => {
      if (node.lang && node.lang === "scala") {
        // Extract template type from meta (defaults to "preview")
        const templateType = extractTemplateType(node.meta);
        
        // Only process code blocks with "preview" or "examples" meta
        if (!node.meta?.includes("preview") && !node.meta?.includes("examples")) {
          return;
        }
        
        // Use sequential counter for this example
        const counter = exampleCounter++;
        examplePathCounters.push({ pathSegments, counter });
        
        // Collect example metadata
        const examplePath = getExampleDirectoryPath(pathSegments, counter, workspaceRoot);
        const millBuildOutPath = getMillBuildOutPath(pathSegments, counter, workspaceRoot);
        const exampleBuildsPath = getExampleBuildsPath(pathSegments, counter, workspaceRoot);
        
        exampleInfos.push({
          counter,
          path: examplePath,
          docPath: docsFilePath,
          millBuildOutPath: millBuildOutPath,
          exampleBuildsPath: exampleBuildsPath,
          lastUpdated: currentTimestamp,
        });
        
        try {
          generateExampleModule(
            pathSegments,
            counter,
            node.value || "",
            workspaceRoot,
            templateType
          );
        } catch (error) {
          console.error(`Failed to generate module for Scala ${templateType}:`, error);
        }
      }
    });

    // Clean up old examples that are no longer in this MDX file
    // Only remove examples that belong to the current doc file
    cleanupOldExamples(examplePathCounters, docsFilePath, workspaceRoot, examplesJson);

    // Update examples.json with new examples
    // Filter out existing examples from this doc file
    const existingExamples = examplesJson.examples.filter(
      ex => ex.docPath !== docsFilePath
    );
    
    // Add new examples from current doc file
    const updatedExamples = [...existingExamples, ...exampleInfos];
    
    // Write updated examples.json
    writeExamplesJson(workspaceRoot, { examples: updatedExamples });
  };
};

