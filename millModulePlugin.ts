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
  type ExampleInfo,
  type ExamplesJson,
  type TemplateContext,
} from "./previewUtils";

/**
 * Generate mill package name for a hash-based example module
 * e.g., laminar_button_abc123 -> 'build.examples.laminar_button_abc123'
 */
const getMillPackageName = (prefix: string, hash: string): string => {
  return `build.examples.${prefix}_${hash}`;
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
  const packageName = `examples.${ctx.prefix}_${ctx.hash}`;
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
  const packageName = `examples.${ctx.prefix}_${ctx.hash}`;
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
 * Ensure the root examples/package.mill exists
 */
const ensureParentModules = (workspaceRoot: string): void => {
  const examplesPath = join(workspaceRoot, "examples");
  const rootPackagePath = join(examplesPath, "package.mill");
  
  if (!existsSync(rootPackagePath)) {
    mkdirSync(examplesPath, { recursive: true });
    writeFileSync(rootPackagePath, createPackageMillContent("build.examples"));
  }
};

/**
 * Generate an example module with package.mill and src/Main.scala
 */
const generateExampleModule = (
  prefix: string,
  hash: string,
  scalaCode: string,
  workspaceRoot: string,
  templateType: "preview" | "examples" = "preview"
): void => {
  const exampleDir = join(workspaceRoot, "examples", `${prefix}_${hash}`);
  const srcDir = join(exampleDir, "src");
  const mainScalaPath = join(srcDir, "Main.scala");
  const packageMillPath = join(exampleDir, "package.mill");
  
  // Ensure directories exist
  mkdirSync(srcDir, { recursive: true });
  
  // Generate Main.scala
  const templateContext: TemplateContext = {
    prefix: prefix,
    hash: hash,
    userCode: scalaCode,
  };
  const scalaSource = applyTemplateByType(templateType, templateContext);
  writeFileSync(mainScalaPath, scalaSource);
  
  // Generate package.mill
  const packageName = getMillPackageName(prefix, hash);
  writeFileSync(packageMillPath, createPackageMillContent(packageName));
};

/**
 * Clean up example modules that no longer exist in the current MDX file
 * Works with flat examples/ directory structure
 * Only removes examples that belong to the current doc file
 * Handles both old "hash_" format and new "{prefix}_" format for backward compatibility
 */
const cleanupOldExamples = (
  currentPrefixHashes: Array<{ prefix: string; hash: string }>,
  docFilePath: string,
  workspaceRoot: string,
  examplesJson: ExamplesJson
): void => {
  const examplesPath = join(workspaceRoot, "examples");
  
  if (!existsSync(examplesPath)) {
    return;
  }
  
  try {
    // Find all examples that belong to the current doc file
    const examplesForCurrentDoc = examplesJson.examples.filter(
      ex => ex.docPath === docFilePath
    );
    const hashesForCurrentDoc = new Set(examplesForCurrentDoc.map(ex => ex.hash));
    
    // Create a set of current prefix_hash combinations for quick lookup
    const currentPrefixHashSet = new Set(
      currentPrefixHashes.map(({ prefix, hash }) => `${prefix}_${hash}`)
    );
    
    const entries = readdirSync(examplesPath);
    
    for (const entry of entries) {
      // Check if it's an old hash-based example directory (starts with "hash_")
      if (entry.startsWith("hash_")) {
        const hash = entry.substring(5); // Remove "hash_" prefix
        
        // Only remove if:
        // 1. This hash belongs to the current doc file (in hashesForCurrentDoc)
        // 2. AND it's not in the current hash list (was removed from the file)
        if (hashesForCurrentDoc.has(hash)) {
          // Check if this hash is still present in current examples (with new prefix format)
          const stillExists = currentPrefixHashes.some(({ hash: currentHash }) => currentHash === hash);
          if (!stillExists) {
            const examplePath = join(examplesPath, entry);
            rmSync(examplePath, { recursive: true, force: true });
          }
        }
      }
      // Check if it's a new prefix-based example directory (format: "{prefix}_{hash}")
      else if (entry.includes("_") && !entry.startsWith("example")) {
        // Extract hash from the entry (assuming format is prefix_hash)
        // We need to check if this entry matches any current examples
        const entryMatches = currentPrefixHashSet.has(entry);
        
        if (!entryMatches) {
          // Check if this entry belongs to the current doc file
          const entryBelongsToDoc = examplesForCurrentDoc.some(ex => {
            // Extract hash from entry by finding the last underscore
            const lastUnderscoreIndex = entry.lastIndexOf("_");
            if (lastUnderscoreIndex === -1) return false;
            const entryHash = entry.substring(lastUnderscoreIndex + 1);
            return ex.hash === entryHash;
          });
          
          if (entryBelongsToDoc) {
            const examplePath = join(examplesPath, entry);
            rmSync(examplePath, { recursive: true, force: true });
          }
        }
      }
      // Also clean up old example{N} directories from previous implementation
      else if (entry.startsWith("example") && !entry.includes("_")) {
        const examplePath = join(examplesPath, entry);
        rmSync(examplePath, { recursive: true, force: true });
      }
    }
  } catch (error) {
    // Ignore errors during cleanup (e.g., directory doesn't exist)
    console.warn(`Failed to cleanup old examples in ${examplesPath}:`, error);
  }
}

/**
 * Get mill build out path for an example
 * e.g., out/examples/laminar_button_abc123/fullLinkJS.dest/main.js
 */
const getMillBuildOutPath = (
  prefix: string,
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["out", "examples", `${prefix}_${hash}`, "fullLinkJS.dest", "main.js"];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example directory path relative to workspace root
 * e.g., examples/laminar_button_abc123
 */
const getExampleDirectoryPath = (
  prefix: string,
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["examples", `${prefix}_${hash}`];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example builds path relative to workspace root
 * e.g., examples-build/laminar_button_abc123.js
 */
const getExampleBuildsPath = (
  prefix: string,
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["examples-build", `${prefix}_${hash}.js`];
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

    // Ensure root examples/package.mill exists
    ensureParentModules(workspaceRoot);

    // Get docs file path relative to workspace root
    const docsFilePath = normalizePath(relative(workspaceRoot, filePath));
    
    // Extract prefix from doc file path
    const prefix = extractPrefixFromDocPath(docsFilePath);

    // Track example prefix/hash combinations
    const examplePrefixHashes: Array<{ prefix: string; hash: string }> = [];
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
        examplePrefixHashes.push({ prefix, hash });
        
        // Collect example metadata
        const examplePath = getExampleDirectoryPath(prefix, hash, workspaceRoot);
        const millBuildOutPath = getMillBuildOutPath(prefix, hash, workspaceRoot);
        const exampleBuildsPath = getExampleBuildsPath(prefix, hash, workspaceRoot);
        
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
            prefix,
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
    cleanupOldExamples(examplePrefixHashes, docsFilePath, workspaceRoot, examplesJson);

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

