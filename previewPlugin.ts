import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root, Parent } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import { join, relative } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync, readFileSync } from "fs";
import { createHash } from "crypto";

/**
 * Generate a short hash from file path, position, and code content for stable identifiers
 */
const hashCode = (filePath: string, line: number | null, column: number | null, code: string): string => {
  // Build hash input: filePath:line:column:code
  const positionStr = line !== null && column !== null ? `${line}:${column}` : '';
  const hashInput = `${filePath}:${positionStr}:${code}`;
  const hash = createHash("sha256").update(hashInput).digest("hex");
  return hash.substring(0, 12); // Use first 12 characters for readability
};

/**
 * Normalize path separators to forward slashes
 */
const normalizePath = (path: string | null | undefined): string => {
  if (!path) return "";
  return path.replace(/\\/g, "/");
};

/**
 * Generate mill package name for a hash-based example module
 * e.g., hash_abc123 -> 'build.examples.hash_abc123'
 */
const getMillPackageName = (hash: string): string => {
  return `build.examples.hash_${hash}`;
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

interface TemplateContext {
  hash: string;
  userCode: string;
}

export const applyTemplate = (ctx: TemplateContext): string => {
  const packageName = `examples.hash_${ctx.hash}`;
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
  const packageName = `examples.hash_${ctx.hash}`;
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
  hash: string,
  scalaCode: string,
  workspaceRoot: string,
  templateType: "preview" | "examples" = "preview"
): void => {
  const exampleDir = join(workspaceRoot, "examples", `hash_${hash}`);
  const srcDir = join(exampleDir, "src");
  const mainScalaPath = join(srcDir, "Main.scala");
  const packageMillPath = join(exampleDir, "package.mill");
  
  // Ensure directories exist
  mkdirSync(srcDir, { recursive: true });
  
  // Generate Main.scala
  const templateContext: TemplateContext = {
    hash: hash,
    userCode: scalaCode,
  };
  const scalaSource = applyTemplateByType(templateType, templateContext);
  writeFileSync(mainScalaPath, scalaSource);
  
  // Generate package.mill
  const packageName = getMillPackageName(hash);
  writeFileSync(packageMillPath, createPackageMillContent(packageName));
};

/**
 * Get the built JavaScript file path for an example
 * e.g., examples-build/hash_abc123.js
 */
const getCompiledJsPath = (
  hash: string,
  workspaceRoot: string
): string => {
  return join(workspaceRoot, "examples-build", `hash_${hash}.js`);
};

/**
 * Read built JavaScript file content
 * Returns null if file doesn't exist or can't be read
 */
const readCompiledJsFile = (filePath: string): string | null => {
  try {
    if (!existsSync(filePath)) {
      return null;
    }
    return readFileSync(filePath, "utf-8");
  } catch (error) {
    console.warn(`Failed to read compiled JS file at ${filePath}:`, error);
    return null;
  }
};

/**
 * Clean up example modules that no longer exist in the current MDX file
 * Works with flat examples/ directory structure
 * Only removes examples that belong to the current doc file
 */
const cleanupOldExamples = (
  currentHashes: string[],
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
    
    const entries = readdirSync(examplesPath);
    
    for (const entry of entries) {
      // Check if it's a hash-based example directory (starts with "hash_")
      if (entry.startsWith("hash_")) {
        const hash = entry.substring(5); // Remove "hash_" prefix
        
        // Only remove if:
        // 1. This hash belongs to the current doc file (in hashesForCurrentDoc)
        // 2. AND it's not in the current hash list (was removed from the file)
        if (hashesForCurrentDoc.has(hash) && !currentHashes.includes(hash)) {
          const examplePath = join(examplesPath, entry);
          rmSync(examplePath, { recursive: true, force: true });
        }
      }
      // Also clean up old example{N} directories from previous implementation
      else if (entry.startsWith("example")) {
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
 * JSON Structure Types
 */
interface ExampleInfo {
  hash: string; // Content hash for stable identification
  path: string; // Example directory path relative to workspace root
  docPath: string; // Docs file path relative to workspace root
  millBuildOutPath: string; // Mill build output path relative to workspace root
  exampleBuildsPath: string; // examples-build path relative to workspace root
  lastUpdated: string; // ISO timestamp string
}

type ExamplesJson = {
  examples: ExampleInfo[];
};

/**
 * Get mill build out path for an example
 * e.g., out/examples/hash_abc123/fullLinkJS.dest/main.js
 */
const getMillBuildOutPath = (
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["out", "examples", `hash_${hash}`, "fullLinkJS.dest", "main.js"];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example directory path relative to workspace root
 * e.g., examples/hash_abc123
 */
const getExampleDirectoryPath = (
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["examples", `hash_${hash}`];
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example builds path relative to workspace root
 * e.g., examples-build/hash_abc123.js
 */
const getExampleBuildsPath = (
  hash: string,
  workspaceRoot: string
): string => {
  const pathParts = ["examples-build", `hash_${hash}.js`];
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

interface PreviewPluginOptions {}

export const previewPlugin: Plugin<[PreviewPluginOptions?], Root> = () => {
  return (tree, file) => {
    const filePath = file.path || file.history?.[0];
    if (!filePath) {
      console.warn("No file path available for preview plugin");
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

    // First pass: collect all Scala preview blocks

    // Track example hashes and nodes for transformation
    const exampleHashes: string[] = [];
    const previewNodes: Array<{
      node: Code;
      hash: string;
      parent: Parent;
      index: number;
    }> = [];
    const exampleInfos: ExampleInfo[] = [];
    const currentTimestamp = new Date().toISOString();
    
    visit(tree, "code", (node, index, parent) => {
      if (node.lang && node.lang === "scala") {
        // Extract template type from meta (defaults to "preview")
        const templateType = extractTemplateType(node.meta);
        
        // Only process code blocks with "preview" or "examples" meta
        if (!node.meta?.includes("preview") && !node.meta?.includes("examples")) {
          return;
        }
        
        // Get position information for hash calculation
        const line = node.position?.start?.line ?? null;
        const column = node.position?.start?.column ?? null;
        
        // Generate hash from file path, position, and code content
        const hash = hashCode(docsFilePath, line, column, node.value || "");
        exampleHashes.push(hash);
        
        // Store node information for second pass transformation
        if (parent && typeof index === "number") {
          previewNodes.push({
            node,
            hash,
            parent: parent as Parent,
            index,
          });
        }
        
        // Collect example metadata
        const examplePath = getExampleDirectoryPath(hash, workspaceRoot);
        const millBuildOutPath = getMillBuildOutPath(hash, workspaceRoot);
        const exampleBuildsPath = getExampleBuildsPath(hash, workspaceRoot);
        
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
    cleanupOldExamples(exampleHashes, docsFilePath, workspaceRoot, examplesJson);

    // Update examples.json with new examples
    // Filter out existing examples from this doc file
    const existingExamples = examplesJson.examples.filter(
      ex => ex.docPath !== docsFilePath
    );
    
    // Add new examples from current doc file
    const updatedExamples = [...existingExamples, ...exampleInfos];
    
    // Write updated examples.json
    writeExamplesJson(workspaceRoot, { examples: updatedExamples });

    // Second pass: transform nodes to Preview components
    for (const { node, hash, parent, index } of previewNodes) {
      // Get built JS file path using hash
      const compiledJsPath = getCompiledJsPath(hash, workspaceRoot);
      
      // Read built JS file content
      const jsContent = readCompiledJsFile(compiledJsPath);
      
      if (jsContent === null) {
        console.warn(`Compiled JS file not found at ${compiledJsPath}, skipping preview transformation`);
        continue;
      }

      // Create MDX JSX element for Preview component
      const previewElement: MdxJsxFlowElement = {
        type: "mdxJsxFlowElement",
        name: "Preview",
        attributes: [
          {
            type: "mdxJsxAttribute",
            name: "code",
            value: jsContent,
          },
          {
            type: "mdxJsxAttribute",
            name: "userCode",
            value: node.value || "",
          },
          {
            type: "mdxJsxAttribute",
            name: "exampleHash",
            value: hash,
          },
        ],
        children: [],
      };

      // Replace the code node with the Preview component
      parent.children[index] = previewElement;
    }
    
  };
};
