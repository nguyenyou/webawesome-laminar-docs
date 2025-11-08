package examples.webawesome.tree.ha1c0c0b18322
  
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
          Tree()(
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
          ),
        )
      )
  })
}
  