import type { VFile } from "vfile";
import { visit } from "unist-util-visit";
import type { Plugin } from "unified";
import type { Code, Root } from "mdast";
import type { MdxJsxFlowElement } from "mdast-util-mdx-jsx";

export const previewPlugin: Plugin<[any], Root> = () => {
  return (tree, vfile) => {
    visit(tree, "code", (node) => {
      // hasVisited is a custom property
      if ("hasVisited" in node) {
        return;
      }

      if (node.lang && node.lang === "scala") {
        // do not anything for pure mode
        if (!node.meta?.includes('preview')) {
          return;
        }

        // generate a module for the code block
        console.log("hi")
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
