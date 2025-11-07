import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import { join, basename, extname } from "path";
import { mkdirSync, writeFileSync, readdirSync, rmSync, existsSync } from "fs";

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

    // Track example numbers for this MDX file
    const exampleNumbers: number[] = [];
    let exampleCounter = 0;

    // Process all code blocks
    visit(tree, "code", (node) => {
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
  };
};
