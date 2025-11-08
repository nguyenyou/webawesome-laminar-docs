package examples.webawesome.tree.example1
  
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
      Tree(_.selection.multiple)(
        TreeItem()(
          "Parent Node",
          TreeItem(_.selected := true)("Child Node 1"),
          TreeItem()(
            "Child Node 2",
            TreeItem()("Child Node 2 - 1"),
            TreeItem()("Child Node 2 - 2")
          )
        )
      )
  })
}
  