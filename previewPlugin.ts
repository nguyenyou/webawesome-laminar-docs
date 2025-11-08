import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root, Parent } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import type { Position } from "unist";
import { join, basename, extname, relative } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync, readFileSync } from "fs";

/**
 * Normalize path separators to forward slashes
 */
const normalizePath = (path: string): string => path.replace(/\\/g, "/");

/**
 * Convert docs file path to examples directory path
 * e.g., content/docs/laminar/components/button.mdx -> examples/laminar/components/button/
 */
const getExamplesPathFromDocsPath = (filePath: string, workspaceRoot: string): string => {
  const normalizedPath = normalizePath(filePath);
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
  
  // Extract filename without extension
  const filename = basename(filePath, extname(filePath));
  
  // Build examples path including the filename
  const examplesPath = join(workspaceRoot, "examples", ...pathAfterDocs, filename);
  return examplesPath;
};

/**
 * Extract module path parts from examples path
 * e.g., examples/laminar/components -> ['laminar', 'components']
 */
const getModulePathParts = (examplesPath: string, workspaceRoot: string): string[] => {
  const normalizedExamplesPath = normalizePath(examplesPath);
  const normalizedWorkspaceRoot = normalizePath(workspaceRoot);
  
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

/**
 * Indent code by adding spaces to each line
 */
const indentCode = (code: string, spaces: number = 4): string => {
  const indent = " ".repeat(spaces);
  return code.split("\n").map(line => `${indent}${line}`).join("\n");
};

/**
 * Generate package.mill content for a Mill module
 */
const createPackageMillContent = (packageName: string): string => {
  return `package ${packageName}\n\nobject \`package\` extends build.WebModule\n`;
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
${indentCode(ctx.userCode, 6)}
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
    writeFileSync(rootPackagePath, createPackageMillContent("build.examples"));
  }
  
  // Create each intermediate level
  for (let i = 0; i < parts.length; i++) {
    currentPath = join(currentPath, parts[i]);
    const packagePath = join(currentPath, "package.mill");
    
    if (!existsSync(packagePath)) {
      mkdirSync(currentPath, { recursive: true });
      const moduleParts = parts.slice(0, i + 1);
      const packageName = getMillPackageName(moduleParts);
      writeFileSync(packagePath, createPackageMillContent(packageName));
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
  writeFileSync(packageMillPath, createPackageMillContent(packageName));
};

/**
 * Get the built JavaScript file path for an example
 * e.g., examples-build/laminar/button/example1.js
 */
const getCompiledJsPath = (
  examplesPath: string,
  exampleNumber: number,
  workspaceRoot: string
): string => {
  const modulePathParts = getModulePathParts(examplesPath, workspaceRoot);
  
  // Build path: examples-build/{modulePathParts}/example{N}.js
  const pathParts = modulePathParts.length > 0
    ? ["examples-build", ...modulePathParts, `example${exampleNumber}.js`]
    : ["examples-build", `example${exampleNumber}.js`];
  
  return join(workspaceRoot, ...pathParts);
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

/**
 * JSON Structure Types
 */
interface ExampleInfo {
  path: string; // Example directory path relative to workspace root
  position: Position | null; // Position in source MDX file
  millBuildOutPath: string; // Mill build output path relative to workspace root
  exampleBuildsPath: string; // examples-build path relative to workspace root
  lastUpdated: string; // ISO timestamp string
}

interface ComponentInfo {
  path: string; // Docs file path relative to workspace root
  examples: ExampleInfo[];
}

type ExamplesJson = {
  [category: string]: ExamplesJson | ComponentInfo;
};

/**
 * Extract component path hierarchy from docs file path
 * e.g., content/docs/laminar/button.mdx -> ['laminar', 'button']
 */
const getComponentPathFromDocsPath = (filePath: string, workspaceRoot: string): string[] => {
  const relativePath = normalizePath(relative(workspaceRoot, filePath));
  const parts = relativePath.split("/");
  
  // Find the index of "docs" folder
  const docsIndex = parts.findIndex((part) => part === "docs");
  
  if (docsIndex === -1) {
    // Fallback: use filename without extension
    const filename = basename(filePath, extname(filePath));
    return [filename];
  }
  
  // Get all parts after "docs" including the filename (without extension)
  const pathAfterDocs = parts.slice(docsIndex + 1);
  const filename = basename(pathAfterDocs[pathAfterDocs.length - 1], extname(pathAfterDocs[pathAfterDocs.length - 1]));
  
  // Replace last part with filename without extension
  const componentPath = [...pathAfterDocs.slice(0, -1), filename];
  return componentPath.filter(part => part.length > 0);
};

/**
 * Get mill build out path for an example
 * e.g., out/examples/laminar/button/example1/fullLinkJS.dest/main.js
 */
const getMillBuildOutPath = (
  modulePathParts: string[],
  exampleNumber: number,
  workspaceRoot: string
): string => {
  const pathParts = modulePathParts.length > 0
    ? ["out", "examples", ...modulePathParts, `example${exampleNumber}`, "fullLinkJS.dest", "main.js"]
    : ["out", "examples", `example${exampleNumber}`, "fullLinkJS.dest", "main.js"];
  
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example directory path relative to workspace root
 * e.g., examples/laminar/button/example1
 */
const getExampleDirectoryPath = (
  modulePathParts: string[],
  exampleNumber: number,
  workspaceRoot: string
): string => {
  const pathParts = modulePathParts.length > 0
    ? ["examples", ...modulePathParts, `example${exampleNumber}`]
    : ["examples", `example${exampleNumber}`];
  
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Get example builds path relative to workspace root
 * e.g., examples-build/laminar/button/example1.js
 */
const getExampleBuildsPath = (
  modulePathParts: string[],
  exampleNumber: number,
  workspaceRoot: string
): string => {
  const pathParts = modulePathParts.length > 0
    ? ["examples-build", ...modulePathParts, `example${exampleNumber}.js`]
    : ["examples-build", `example${exampleNumber}.js`];
  
  return normalizePath(relative(workspaceRoot, join(workspaceRoot, ...pathParts)));
};

/**
 * Set nested value in object using path array
 */
const setNestedValue = (obj: ExamplesJson, path: string[], value: ComponentInfo): void => {
  let current: ExamplesJson = obj;
  
  for (let i = 0; i < path.length - 1; i++) {
    const key = path[i];
    if (!current[key] || typeof current[key] !== "object" || Array.isArray(current[key])) {
      current[key] = {};
    }
    current = current[key] as ExamplesJson;
  }
  
  const lastKey = path[path.length - 1];
  current[lastKey] = value;
};

/**
 * Get nested value from object using path array
 */
const getNestedValue = (obj: ExamplesJson, path: string[]): ComponentInfo | ExamplesJson | undefined => {
  let current: ExamplesJson | ComponentInfo = obj;
  
  for (const key of path) {
    if (typeof current !== "object" || current === null || Array.isArray(current)) {
      return undefined;
    }
    // Check if it's a ComponentInfo (has 'path' and 'examples' properties)
    if ('path' in current && 'examples' in current) {
      // Can't traverse further, ComponentInfo is a leaf node
      return undefined;
    }
    // It's an ExamplesJson, check if key exists
    if (!(key in current)) {
      return undefined;
    }
    current = (current as ExamplesJson)[key] as ExamplesJson | ComponentInfo;
  }
  
  return current;
};

/**
 * Read existing examples.json file
 */
const readExamplesJson = (workspaceRoot: string): ExamplesJson => {
  const examplesJsonPath = join(workspaceRoot, "examples.json");
  
  if (!existsSync(examplesJsonPath)) {
    return {};
  }
  
  try {
    const content = readFileSync(examplesJsonPath, "utf-8");
    return JSON.parse(content) as ExamplesJson;
  } catch (error) {
    console.warn(`Failed to read examples.json:`, error);
    return {};
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

    // Get examples path from docs path
    const examplesPath = getExamplesPathFromDocsPath(filePath, workspaceRoot);
    
    // Get component path hierarchy from docs file path
    const componentPath = getComponentPathFromDocsPath(filePath, workspaceRoot);
    
    // Get module path parts for building paths
    const modulePathParts = getModulePathParts(examplesPath, workspaceRoot);
    
    // Ensure all parent modules exist
    ensureParentModules(examplesPath, workspaceRoot);

    // Get docs file path relative to workspace root
    const docsFilePath = normalizePath(relative(workspaceRoot, filePath));

    // First pass: collect all Scala preview blocks

    // Track example numbers and nodes for transformation
    const exampleNumbers: number[] = [];
    const previewNodes: Array<{
      node: Code;
      exampleNumber: number;
      parent: Parent;
      index: number;
    }> = [];
    const exampleInfos: ExampleInfo[] = [];
    let exampleCounter = 0;
    const currentTimestamp = new Date().toISOString();
    
    visit(tree, "code", (node, index, parent) => {
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
            parent: parent as Parent,
            index,
          });
        }
        
        // Collect example metadata
        const examplePath = getExampleDirectoryPath(modulePathParts, exampleCounter, workspaceRoot);
        const millBuildOutPath = getMillBuildOutPath(modulePathParts, exampleCounter, workspaceRoot);
        const exampleBuildsPath = getExampleBuildsPath(modulePathParts, exampleCounter, workspaceRoot);
        
        exampleInfos.push({
          path: examplePath,
          position: node.position || null,
          millBuildOutPath: millBuildOutPath,
          exampleBuildsPath: exampleBuildsPath,
          lastUpdated: currentTimestamp,
        });
        
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

    // Update examples.json with new component info
    if (exampleInfos.length > 0) {
      const componentInfo: ComponentInfo = {
        path: docsFilePath,
        examples: exampleInfos,
      };
      
      setNestedValue(examplesJson, componentPath, componentInfo);
    } else {
      // If no examples, remove the component entry
      const existing = getNestedValue(examplesJson, componentPath);
      if (existing && 'path' in existing) {
        // Remove the component entry by setting parent to empty object
        if (componentPath.length > 0) {
          const parentPath = componentPath.slice(0, -1);
          const lastKey = componentPath[componentPath.length - 1];
          const parent = parentPath.length > 0 
            ? getNestedValue(examplesJson, parentPath) as ExamplesJson
            : examplesJson;
          if (parent && typeof parent === 'object' && !Array.isArray(parent)) {
            delete parent[lastKey];
          }
        }
      }
    }

    // Write updated examples.json
    writeExamplesJson(workspaceRoot, examplesJson);

    // Second pass: transform nodes to Preview components
    for (const { node, exampleNumber, parent, index } of previewNodes) {
      // Get built JS file path
      const compiledJsPath = getCompiledJsPath(examplesPath, exampleNumber, workspaceRoot);
      
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
        ],
        children: [],
      };

      // Replace the code node with the Preview component
      parent.children[index] = previewElement;
    }
    
  };
};
