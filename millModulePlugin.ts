import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Root } from "mdast";
import { join, relative } from "path";
import { mkdirSync, writeFileSync, existsSync } from "fs";
import {
  normalizePath,
  extractHierarchicalPathSegments,
  type TemplateContext,
} from "./previewUtils";
import { applyTemplateByType, toCamelCase } from "./templateProcessor";

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

    // Get docs file path relative to workspace root
    const docsFilePath = normalizePath(relative(workspaceRoot, filePath));
    
    // Extract hierarchical path segments from doc file path
    const pathSegments = extractHierarchicalPathSegments(docsFilePath);
    
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
  };
};

