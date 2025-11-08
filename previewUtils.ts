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
 * Get the built JavaScript file path for an example
 * e.g., examples-build/laminar_button_abc123.js
 */
export const getCompiledJsPath = (
  prefix: string,
  hash: string,
  workspaceRoot: string
): string => {
  return join(workspaceRoot, "examples-build", `${prefix}_${hash}.js`);
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
  hash: string; // Content hash for stable identification
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
  hash: string;
  userCode: string;
}

