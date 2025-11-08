package examples.webawesome.tree.he8856ebde552
  
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
          div(
            styleTag("""
              .custom-icons wa-tree-item::part(expand-button) {
                /* Disable the expand/collapse animation */
                rotate: none;
              }
            """),
            Tree(
              _.slots.expandIcon(Icon(_.name := "square-plus", _.variant := "solid")()),
              _.slots.collapseIcon(Icon(_.name := "square-minus", _.variant := "solid")())
            )(
              cls("custom-icons"),
              TreeItem()(
                "Deciduous",
                TreeItem()("Birch"),
                TreeItem()(
                  "Maple",
                  TreeItem()("Field maple"),
                  TreeItem()("Red maple"),
                  TreeItem()("Sugar maple")
                ),
                TreeItem()("Oak")
              ),
              TreeItem()(
                "Coniferous",
                TreeItem()("Cedar"),
                TreeItem()("Pine"),
                TreeItem()("Spruce")
              ),
              TreeItem()(
                "Non-trees",
                TreeItem()("Bamboo"),
                TreeItem()("Cactus"),
                TreeItem()("Fern")
              )
            )
          ),
        )
      )
  })
}
  