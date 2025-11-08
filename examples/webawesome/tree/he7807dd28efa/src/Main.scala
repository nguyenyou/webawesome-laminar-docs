package examples.webawesome.tree.he7807dd28efa
  
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
          Tree(_.style := "--indent-guide-width: 1px")(
            TreeItem(_.expanded := true)(
              "Deciduous",
              TreeItem()("Birch"),
              TreeItem(_.expanded := true)(
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
  