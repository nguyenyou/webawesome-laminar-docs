package examples.webawesome.tree.h4c65ed34d2a9
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ExampleGroups(
        Examples(
          val selectionMode = Var(TreeSelection.single.value),
        ),
        Examples(
          div(
            tw.spaceY4,
            Select(
              _.label := "Selection",
              _.value <-- selectionMode,
              _.onInput.mapToValue.map(_.asInstanceOf[SharedTypes.TreeSelection]) --> selectionMode
            )(
              UOption(_.value := TreeSelection.single.value)("Single"),
              UOption(_.value := TreeSelection.multiple.value)("Multiple"),
              UOption(_.value := TreeSelection.leaf.value)("Leaf")
            ),
            Tree(_.selection <-- selectionMode)(
              inContext { ctx =>
                selectionMode --> Observer { _ =>
                  ctx.ref.querySelectorAll("wa-tree-item").foreach { item =>
                    item.asInstanceOf[TreeItem.Ref].selected = false
                  }
                }
              },
              TreeItem()(
                "Item 1",
                TreeItem()(
                  "Item A",
                  TreeItem()("Item Z"),
                  TreeItem()("Item Y"),
                  TreeItem()("Item X")
                ),
                TreeItem()("Item B"),
                TreeItem()("Item C")
              ),
              TreeItem()("Item 2"),
              TreeItem()("Item 3")
            )
          ),
        )
      )
  })
}
  