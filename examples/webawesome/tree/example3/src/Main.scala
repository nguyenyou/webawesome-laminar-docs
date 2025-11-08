package examples.webawesome.tree.example3
  
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
      val selectionMode = Var(TreeSelection.single.value)
      
      div(
        cls("space-y-4"),
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
      )
  })
}
  