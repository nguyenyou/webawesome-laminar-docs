package examples.webawesome.tree.h2b1a0eee8894
  
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
      )
  })
}
  