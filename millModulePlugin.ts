import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Root } from "mdast";
import { join, relative } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync, readFileSync } from "fs";
import {
  hashCode,
  normalizePath,
  extractPrefixFromDocPath,
  extractHierarchicalPathSegments,
  joinHierarchicalPath,
  type ExampleInfo,
  type ExamplesJson,
  type TemplateContext,
} from "./previewUtils";

/**
 * Generate mill package name for a hierarchical example module
 * e.g., ["webawesome", "button"], "abc123" -> 'build.examples.webawesome.button.habc123'
 */
const getMillPackageName = (pathSegments: string[], hash: string): string => {
  const hHash = `h${hash}`; // Prefix hash with "h" for valid package name
  const packagePath = pathSegments.join(".");
  return `build.examples.${packagePath}.${hHash}`;
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
  // ctx.prefix is now the hierarchical path joined with "/"
  const packageName = `examples.${ctx.prefix.replace(/\//g, ".")}.h${ctx.hash}`;
  const userCode = ctx.userCode || "";
  
  return `package ${packageName}
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

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
 * Apply examples template: converts newline-separated code into comma-separated arguments
 * wrapped in Examples(...) call. Only adds commas between complete top-level expressions,
 * preserving nested structures.
 */
export const applyExamplesTemplate = (ctx: TemplateContext): string => {
  // ctx.prefix is now the hierarchical path joined with "/"
  const packageName = `examples.${ctx.prefix.replace(/\//g, ".")}.h${ctx.hash}`;
  const userCode = ctx.userCode || "";
  
  // Parse into top-level expressions (preserving nested structure)
  const expressions = parseTopLevelExpressions(userCode);
  
  // Join expressions with commas, preserving their internal structure
  const examplesArgs = expressions
    .map(expr => expr.endsWith(",") ? expr : `${expr},`)
    .join("\n");
  
  // Wrap in Examples(...) call
  const examplesCall = `Examples(
${indentCode(examplesArgs, 2)}
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
${indentCode(examplesCall, 6)}
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
 * e.g., examples/webawesome/button/package.mill
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
    currentPath = join(currentPath, segment);
    packageName = `${packageName}.${segment}`;
    
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
 * Uses hierarchical directory structure: examples/{category}/{component}/h{hash}/
 */
const generateExampleModule = (
  pathSegments: string[],
  hash: string,
  scalaCode: string,
  workspaceRoot: string,
  templateType: "preview" | "examples" = "preview"
): void => {
  // Ensure parent modules exist
  ensureParentModules(workspaceRoot, pathSegments);
  
  // Create hierarchical path: examples/webawesome/button/h{hash}
  const hHash = `h${hash}`;
  const hierarchicalPath = joinHierarchicalPath(pathSegments);
  const exampleDir = join(workspaceRoot, "examples", hierarchicalPath, hHash);
  const srcDir = join(exampleDir, "src");
  const mainScalaPath = join(srcDir, "Main.scala");
  const packageMillPath = join(exampleDir, "package.mill");
  
  // Ensure directories exist
  mkdirSync(srcDir, { recursive: true });
  
  // Generate Main.scala
  // TemplateContext.prefix should be the hierarchical path joined with "/"
  const templateContext: TemplateContext = {
    prefix: hierarchicalPath,
    hash: hash,
    userCode: scalaCode,
  };
  const scalaSource = applyTemplateByType(templateType, templateContext);
  writeFileSync(mainScalaPath, scalaSource);
  
  // Generate package.mill
  const packageName = getMillPackageName(pathSegments, hash);
  writeFileSync(packageMillPath, createPackageMillContent(packageName));
};

/**
 * Recursively clean up example modules that no longer exist
 * Handles both old flat structure and new hierarchical structure
 */
const cleanupOldExamplesRecursive = (
  currentHashes: Set<string>,
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
        // Check if this is an old flat structure directory (format: "{prefix}_{hash}" or "hash_{hash}")
        if (entry.name.includes("_") && !entry.name.startsWith("h")) {
          // Old flat structure: extract hash and check if it should be removed
          const lastUnderscoreIndex = entry.name.lastIndexOf("_");
          if (lastUnderscoreIndex !== -1) {
            const entryHash = entry.name.substring(lastUnderscoreIndex + 1);
            
            // Check if this hash belongs to the current doc file
            const examplesForCurrentDoc = examplesJson.examples.filter(
              ex => ex.docPath === docFilePath && ex.hash === entryHash
            );
            
            // Remove if it doesn't exist in current hashes
            if (examplesForCurrentDoc.length > 0 && !currentHashes.has(entryHash)) {
              rmSync(entryPath, { recursive: true, force: true });
              continue;
            }
          }
        }
        // Check if this is an old "hash_" directory
        else if (entry.name.startsWith("hash_")) {
          const entryHash = entry.name.substring(5);
          const examplesForCurrentDoc = examplesJson.examples.filter(
            ex => ex.docPath === docFilePath && ex.hash === entryHash
          );
          if (examplesForCurrentDoc.length > 0 && !currentHashes.has(entryHash)) {
            rmSync(entryPath, { recursive: true, force: true });
            continue;
          }
        }
        // Check if this is an old "example{N}" directory
        else if (entry.name.startsWith("example") && !entry.name.includes("_")) {
          rmSync(entryPath, { recursive: true, force: true });
          continue;
        }
        // Otherwise, recursively process subdirectories (for hierarchical structure)
        else {
          cleanupOldExamplesRecursive(
            currentHashes,
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
  currentPathHashes: Array<{ pathSegments: string[]; hash: string }>,
  docFilePath: string,
  workspaceRoot: string,
  examplesJson: ExamplesJson
): void => {
  const examplesPath = join(workspaceRoot, "examples");
  
  if (!existsSync(examplesPath)) {
    return;
  }
  
  // Create a set of current hashes for quick lookup
  const currentHashes = new Set(currentPathHashes.map(({ hash }) => hash));
  
  // Recursively clean up old examples
  cleanupOldExamplesRecursive(
    currentHashes,
    docFilePath,
    examplesJson,
    examplesPath,
    workspaceRoot
  );
}

/**
 * Get mill build out path for an example
 * e.g., out/examples/webawesome/button/habc123/fullLinkJS.dest/main.js
 */
const getMillBuildOutPath = (
  pathSegments: string[],
  hash: string,
  workspaceRoot: string
): string => {
  const hHash = `h${hash}`;
  const hierarchicalPath = joinHierarchicalPath(pathSegments);
  const pathParts = ["out", "examples", hierarchicalPath, hHash, "fullLinkJS.dest", "main.js"];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example directory path relative to workspace root
 * e.g., examples/webawesome/button/habc123
 */
const getExampleDirectoryPath = (
  pathSegments: string[],
  hash: string,
  workspaceRoot: string
): string => {
  const hHash = `h${hash}`;
  const hierarchicalPath = joinHierarchicalPath(pathSegments);
  const pathParts = ["examples", hierarchicalPath, hHash];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example builds path relative to workspace root
 * Uses flat structure for builds: examples-build/webawesome_button_abc123.js
 * (keeping flat structure for backward compatibility with build system)
 */
const getExampleBuildsPath = (
  pathSegments: string[],
  hash: string,
  workspaceRoot: string
): string => {
  // For builds, use flattened prefix format for backward compatibility
  const flattenedPrefix = pathSegments.join("_");
  const pathParts = ["examples-build", `${flattenedPrefix}_${hash}.js`];
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

    // Track example path segments/hash combinations
    const examplePathHashes: Array<{ pathSegments: string[]; hash: string }> = [];
    const exampleInfos: ExampleInfo[] = [];
    const currentTimestamp = new Date().toISOString();
    
    visit(tree, "code", (node) => {
      if (node.lang && node.lang === "scala") {
        // Extract template type from meta (defaults to "preview")
        const templateType = extractTemplateType(node.meta);
        
        // Only process code blocks with "preview" or "examples" meta
        if (!node.meta?.includes("preview") && !node.meta?.includes("examples")) {
          return;
        }
        
        // Generate hash from code content and meta
        const hash = hashCode(node.value || "", node.meta);
        examplePathHashes.push({ pathSegments, hash });
        
        // Collect example metadata
        const examplePath = getExampleDirectoryPath(pathSegments, hash, workspaceRoot);
        const millBuildOutPath = getMillBuildOutPath(pathSegments, hash, workspaceRoot);
        const exampleBuildsPath = getExampleBuildsPath(pathSegments, hash, workspaceRoot);
        
        exampleInfos.push({
          hash,
          path: examplePath,
          docPath: docsFilePath,
          millBuildOutPath: millBuildOutPath,
          exampleBuildsPath: exampleBuildsPath,
          lastUpdated: currentTimestamp,
        });
        
        try {
          generateExampleModule(
            pathSegments,
            hash,
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
    cleanupOldExamples(examplePathHashes, docsFilePath, workspaceRoot, examplesJson);

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

