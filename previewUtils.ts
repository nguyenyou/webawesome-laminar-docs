import { join } from "path";
import { existsSync, readFileSync } from "fs";
import { createHash } from "crypto";

/**
 * Generate a short hash from code content and meta for stable identifiers
 */
export const hashCode = (code: string, meta: string | null | undefined): string => {
  const metaStr = meta || "";
  const hashInput = `${code}:${metaStr}`;
  const hash = createHash("sha256").update(hashInput).digest("hex");
  return hash.substring(0, 12); // Use first 12 characters for readability
};

/**
 * Normalize path separators to forward slashes
 */
export const normalizePath = (path: string | null | undefined): string => {
  if (!path) return "";
  return path.replace(/\\/g, "/");
};

/**
 * Extract prefix from doc file path for meaningful example directory names
 * e.g., content/docs/laminar/button.mdx -> laminar_button
 * @deprecated Use extractHierarchicalPathSegments for new hierarchical structure
 */
export const extractPrefixFromDocPath = (docPath: string): string => {
  let path = normalizePath(docPath);
  
  // Remove content/docs/ prefix if present
  if (path.startsWith("content/docs/")) {
    path = path.substring("content/docs/".length);
  }
  
  // Remove .mdx extension
  if (path.endsWith(".mdx")) {
    path = path.substring(0, path.length - 4);
  }
  
  // Replace path separators with underscores
  path = path.replace(/\//g, "_");
  
  // Sanitize: remove any invalid characters for directory names
  // Keep only alphanumeric, underscores, and hyphens
  path = path.replace(/[^a-zA-Z0-9_-]/g, "_");
  
  // Remove consecutive underscores
  path = path.replace(/_+/g, "_");
  
  // Remove leading/trailing underscores
  path = path.replace(/^_+|_+$/g, "");
  
  return path || "example"; // Fallback to "example" if empty
};

/**
 * Extract hierarchical path segments from doc file path
 * e.g., content/docs/webawesome/button.mdx -> ["webawesome", "button"]
 * Returns an array of path segments representing the hierarchy
 */
export const extractHierarchicalPathSegments = (docPath: string): string[] => {
  let path = normalizePath(docPath);
  
  // Remove content/docs/ prefix if present
  if (path.startsWith("content/docs/")) {
    path = path.substring("content/docs/".length);
  }
  
  // Remove .mdx extension
  if (path.endsWith(".mdx")) {
    path = path.substring(0, path.length - 4);
  }
  
  // Split by path separators
  const segments = path.split("/").filter(segment => segment.length > 0);
  
  // Sanitize each segment: remove invalid characters for directory names
  // Keep only alphanumeric, underscores, and hyphens
  const sanitizedSegments = segments.map(segment => {
    let sanitized = segment.replace(/[^a-zA-Z0-9_-]/g, "_");
    // Remove consecutive underscores
    sanitized = sanitized.replace(/_+/g, "_");
    // Remove leading/trailing underscores
    sanitized = sanitized.replace(/^_+|_+$/g, "");
    return sanitized || "example"; // Fallback to "example" if empty
  });
  
  return sanitizedSegments.length > 0 ? sanitizedSegments : ["example"];
};

/**
 * Join hierarchical path segments into a directory path
 * e.g., ["webawesome", "button"] -> "webawesome/button"
 */
export const joinHierarchicalPath = (segments: string[]): string => {
  return segments.join("/");
};

/**
 * Get the built JavaScript file path for an example
 * e.g., examples-build/webawesome_button_example1.js
 */
export const getCompiledJsPath = (
  prefix: string,
  counter: number,
  workspaceRoot: string
): string => {
  return join(workspaceRoot, "examples-build", `${prefix}_example${counter}.js`);
};

/**
 * Read built JavaScript file content
 * Returns null if file doesn't exist or can't be read
 */
export const readCompiledJsFile = (filePath: string): string | null => {
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
 * JSON Structure Types
 */
export interface ExampleInfo {
  counter: number; // Sequential counter for example identification (per MDX file)
  path: string; // Example directory path relative to workspace root
  docPath: string; // Docs file path relative to workspace root
  millBuildOutPath: string; // Mill build output path relative to workspace root
  exampleBuildsPath: string; // examples-build path relative to workspace root
  lastUpdated: string; // ISO timestamp string
}

export type ExamplesJson = {
  examples: ExampleInfo[];
};

export interface TemplateContext {
  prefix: string;
  counter: number;
  userCode: string;
}

