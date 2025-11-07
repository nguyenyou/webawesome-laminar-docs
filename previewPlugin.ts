import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";
import { join, basename, extname } from "path";

const getDocNameFromPath = (filePath: string) => {
  // Normalize the path and split by path separator
  const normalizedPath = filePath.replace(/\\/g, "/");
  const parts = normalizedPath.split("/");

  // Find the index of "docs" folder
  const docsIndex = parts.findIndex((part) => part === "docs");

  if (docsIndex !== -1 && docsIndex < parts.length - 1) {
    // Check if there's a subfolder after "docs"
    // e.g., ["content", "docs", "react", "sjs.mdx"] -> docsIndex=1, next part is "react"
    const nextPart = parts[docsIndex + 1];
    const filename = parts[parts.length - 1];
    
    // If next part is not a filename (doesn't have extension), it's a subfolder
    if (nextPart && !extname(nextPart) && nextPart !== filename) {
      return nextPart;
    }
  }
  
  // Fallback to filename without extension
  const filename = basename(filePath);
  const nameWithoutExt = extname(filename) ? filename.replace(extname(filename), '') : filename;
  return nameWithoutExt || 'default';
};
export const previewPlugin: Plugin<[any], Root> = () => {
  return (tree, file) => {
    // Extract doc name from file path
    const docName = getDocNameFromPath(file.path || file.history?.[0] || 'default');
    console.log(docName);

    let exampleCounter = 0;
    visit(tree, "code", (node) => {
      // hasVisited is a custom property
      if ("hasVisited" in node) {
        return;
      }

      if (node.lang && node.lang === "scala") {
        // do not anything for pure mode
        if (!node.meta?.includes("preview")) {
          return;
        }
        exampleCounter++;
        const scalaSource = node.value;

        // generate a module for the code block
        console.log("hi");
      }
    });
  };
};
// export function previewPlugin() {
//   return (tree: Node, file: VFile) => {
//     visit(tree, "code", node => {
//         console.log("hi")
//     });
//   };
// }
