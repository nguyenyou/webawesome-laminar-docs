import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root, Parent } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import { relative } from "path";
import {
  hashCode,
  normalizePath,
  extractPrefixFromDocPath,
  getCompiledJsPath,
  readCompiledJsFile,
} from "./previewUtils";

interface PreviewTransformPluginOptions {}

export const previewTransformPlugin: Plugin<[PreviewTransformPluginOptions?], Root> = () => {
  return (tree, file) => {
    const filePath = file.path || file.history?.[0];
    if (!filePath) {
      console.warn("No file path available for preview transform plugin");
      return;
    }

    // Use file.cwd as the workspace root (current working directory)
    const workspaceRoot = file.cwd || process.cwd();

    // Get docs file path relative to workspace root
    const docsFilePath = normalizePath(relative(workspaceRoot, filePath));
    
    // Extract prefix from doc file path
    const prefix = extractPrefixFromDocPath(docsFilePath);

    // Track nodes for transformation
    const previewNodes: Array<{
      node: Code;
      prefix: string;
      hash: string;
      parent: Parent;
      index: number;
    }> = [];
    
    visit(tree, "code", (node, index, parent) => {
      if (node.lang && node.lang === "scala") {
        // Only process code blocks with "preview" or "examples" meta
        if (!node.meta?.includes("preview") && !node.meta?.includes("examples")) {
          return;
        }
        
        // Generate hash from code content and meta (must match millModulePlugin)
        const hash = hashCode(node.value || "", node.meta);
        
        // Store node information for transformation
        if (parent && typeof index === "number") {
          previewNodes.push({
            node,
            prefix,
            hash,
            parent: parent as Parent,
            index,
          });
        }
      }
    });

    // Transform nodes to Preview components
    for (const { node, prefix, hash, parent, index } of previewNodes) {
      // Get built JS file path using prefix and hash
      const compiledJsPath = getCompiledJsPath(prefix, hash, workspaceRoot);
      
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

