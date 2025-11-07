import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import { join, basename, extname } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync, readFileSync } from "fs";

/**
 * Convert docs file path to examples directory path
 * e.g., content/docs/laminar/components/button.mdx -> examples/laminar/components/
 */
const getExamplesPathFromDocsPath = (filePath: string, workspaceRoot: string): string => {
  const normalizedPath = filePath.replace(/\\/g, "/");
  const parts = normalizedPath.split("/");
  
  // Find the index of "docs" folder
  const docsIndex = parts.findIndex((part) => part === "docs");
  
  if (docsIndex === -1) {
    // Fallback: use filename without extension as category
    const filename = basename(filePath, extname(filePath));
    return join(workspaceRoot, "examples", filename);
  }
  
  // Get all parts after "docs" excluding the filename
  const pathAfterDocs = parts.slice(docsIndex + 1, -1);
  
  // Build examples path
  const examplesPath = join(workspaceRoot, "examples", ...pathAfterDocs);
  return examplesPath;
};

/**
 * Extract module path parts from examples path
 * e.g., examples/laminar/components -> ['laminar', 'components']
 */
const getModulePathParts = (examplesPath: string, workspaceRoot: string): string[] => {
  const normalizedExamplesPath = examplesPath.replace(/\\/g, "/");
  const normalizedWorkspaceRoot = workspaceRoot.replace(/\\/g, "/");
  
  // Remove workspace root and "examples" prefix
  const relativePath = normalizedExamplesPath
    .replace(normalizedWorkspaceRoot, "")
    .replace(/^\/+/, "")
    .replace(/\/+$/, "");
  
  const parts = relativePath.split("/");
  
  // Remove "examples" if it's the first part
  if (parts[0] === "examples") {
    return parts.slice(1);
  }
  
  return parts;
};

/**
 * Generate mill package name from path parts
 * e.g., ['laminar', 'components'] -> 'build.examples.laminar.components'
 */
const getMillPackageName = (parts: string[]): string => {
  if (parts.length === 0) {
    return "build.examples";
  }
  return `build.examples.${parts.join(".")}`;
};

interface TemplateContext {
  number: number;
  userCode: string;
  modulePathParts: string[];
}

export const applyTemplate = (ctx: TemplateContext): string => {
  const packageName = ctx.modulePathParts.length > 0
    ? `examples.${ctx.modulePathParts.join(".")}.example${ctx.number}`
    : `examples.example${ctx.number}`;
  
  return `package ${packageName}
  
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*

  @main def app = {
    val container = dom.document.querySelector("#root")
    render(container, {
      ${ctx.userCode.split("\n").join("\n    ")}
    })
  }
  `;
};

/**
 * Recursively ensure all parent modules exist with package.mill files
 */
const ensureParentModules = (examplesPath: string, workspaceRoot: string): void => {
  const parts = getModulePathParts(examplesPath, workspaceRoot);
  
  // Build path from root, creating each level
  let currentPath = join(workspaceRoot, "examples");
  
  // Ensure root examples/package.mill exists
  const rootPackagePath = join(currentPath, "package.mill");
  if (!existsSync(rootPackagePath)) {
    mkdirSync(currentPath, { recursive: true });
    writeFileSync(rootPackagePath, "package build.examples\n\nobject `package` extends build.WebModule\n");
  }
  
  // Create each intermediate level
  for (let i = 0; i < parts.length; i++) {
    currentPath = join(currentPath, parts[i]);
    const packagePath = join(currentPath, "package.mill");
    
    if (!existsSync(packagePath)) {
      mkdirSync(currentPath, { recursive: true });
      const moduleParts = parts.slice(0, i + 1);
      const packageName = getMillPackageName(moduleParts);
      writeFileSync(packagePath, `package ${packageName}\n\nobject \`package\` extends build.WebModule\n`);
    }
  }
};

/**
 * Generate an example module with package.mill and src/Main.scala
 */
const generateExampleModule = (
  examplesPath: string,
  exampleNumber: number,
  scalaCode: string,
  workspaceRoot: string
): void => {
  const modulePathParts = getModulePathParts(examplesPath, workspaceRoot);
  const exampleDir = join(examplesPath, `example${exampleNumber}`);
  const srcDir = join(exampleDir, "src");
  const mainScalaPath = join(srcDir, "Main.scala");
  const packageMillPath = join(exampleDir, "package.mill");
  
  // Ensure directories exist
  mkdirSync(srcDir, { recursive: true });
  
  // Generate Main.scala
  const templateContext: TemplateContext = {
    number: exampleNumber,
    userCode: scalaCode,
    modulePathParts: modulePathParts,
  };
  const scalaSource = applyTemplate(templateContext);
  writeFileSync(mainScalaPath, scalaSource);
  
  // Generate package.mill
  const packageParts = [...modulePathParts, `example${exampleNumber}`];
  const packageName = getMillPackageName(packageParts);
  writeFileSync(packageMillPath, `package ${packageName}\n\nobject \`package\` extends build.WebModule\n`);
};

/**
 * Get the compiled JavaScript file path for an example
 * e.g., out/examples/laminar/example1/fullLinkJS.dest/main.js
 */
const getCompiledJsPath = (
  examplesPath: string,
  exampleNumber: number,
  workspaceRoot: string
): string => {
  const modulePathParts = getModulePathParts(examplesPath, workspaceRoot);
  
  // Build path: out/examples/{modulePathParts}/example{N}/fullLinkJS.dest/main.js
  const pathParts = modulePathParts.length > 0
    ? ["out", "examples", ...modulePathParts, `example${exampleNumber}`, "fullLinkJS.dest", "main.js"]
    : ["out", "examples", `example${exampleNumber}`, "fullLinkJS.dest", "main.js"];
  
  return join(workspaceRoot, ...pathParts);
};

/**
 * Read compiled JavaScript file content
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
 */
const cleanupOldExamples = (
  examplesPath: string,
  currentExampleNumbers: number[],
  workspaceRoot: string
): void => {
  if (!existsSync(examplesPath)) {
    return;
  }
  
  try {
    const entries = readdirSync(examplesPath);
    
    for (const entry of entries) {
      // Check if it's an example directory (starts with "example" followed by a number)
      if (entry.startsWith("example")) {
        const match = entry.match(/^example(\d+)$/);
        if (match) {
          const exampleNumber = parseInt(match[1], 10);
          
          // If this example number is not in the current list, remove it
          if (!currentExampleNumbers.includes(exampleNumber)) {
            const examplePath = join(examplesPath, entry);
            rmSync(examplePath, { recursive: true, force: true });
          }
        }
      }
    }
  } catch (error) {
    // Ignore errors during cleanup (e.g., directory doesn't exist)
    console.warn(`Failed to cleanup old examples in ${examplesPath}:`, error);
  }
}

export const previewPlugin: Plugin<[any], Root> = () => {
  return (tree, file) => {
    const filePath = file.path || file.history?.[0];
    if (!filePath) {
      console.warn("No file path available for preview plugin");
      return;
    }

    // Use file.cwd as the workspace root (current working directory)
    const workspaceRoot = file.cwd || process.cwd();

    // Get examples path from docs path
    const examplesPath = getExamplesPathFromDocsPath(filePath, workspaceRoot);
    
    // Ensure all parent modules exist
    ensureParentModules(examplesPath, workspaceRoot);

    // First pass: collect all Scala preview blocks

    // Track example numbers and nodes for transformation
    const exampleNumbers: number[] = [];
    const previewNodes: Array<{
      node: Code;
      exampleNumber: number;
      parent: any;
      index: number;
    }> = [];
    let exampleCounter = 0;
    
    visit(tree, "code", (node, index, parent) => {
      // hasVisited is a custom property
      if ("hasVisited" in node) {
        return;
      }

      if (node.lang && node.lang === "scala") {
        // Only process code blocks with "preview" meta
        if (!node.meta?.includes("preview")) {
          return;
        }
        
        exampleCounter++;
        exampleNumbers.push(exampleCounter);
        
        // Store node information for second pass transformation
        if (parent && typeof index === "number") {
          previewNodes.push({
            node,
            exampleNumber: exampleCounter,
            parent,
            index,
          });
        }
        
        try {
          generateExampleModule(
            examplesPath,
            exampleCounter,
            node.value || "",
            workspaceRoot
          );
        } catch (error) {
          console.error(`Failed to generate module for Scala preview:`, error);
        }
      }
    });

    // Clean up old examples that are no longer in this MDX file
    cleanupOldExamples(examplesPath, exampleNumbers, workspaceRoot);

    // Second pass: transform nodes to Preview components
    for (const { node, exampleNumber, parent, index } of previewNodes) {
      // Get compiled JS file path
      const compiledJsPath = getCompiledJsPath(examplesPath, exampleNumber, workspaceRoot);
      
      // Read compiled JS file content
      const jsContent = readCompiledJsFile(compiledJsPath);
      
      if (jsContent === null) {
        console.warn(`Compiled JS file not found at ${compiledJsPath}, skipping transformation`);
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
        ],
        children: [],
      };

      // Replace the code node with the Preview component
      parent.children[index] = previewElement;
    }
    
  };
};
