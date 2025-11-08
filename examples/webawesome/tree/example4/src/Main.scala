package examples.webawesome.tree.example4
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
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
      )
  })
}
  