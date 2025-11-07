import type { VFile } from "vfile";
import { visit } from "unist-util-visit";

export function previewPlugin() {
  return (tree: Node, file: VFile) => {
    visit(tree, "code", (node: any) => {
        console.log("hi")
    });
  };
}
