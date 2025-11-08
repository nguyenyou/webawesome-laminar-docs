package examples.webawesome.tree.example5
  
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
      )
  })
}
  