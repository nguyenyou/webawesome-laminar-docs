package examples.webawesome.buttonGroup.h3031509f5ac9
  
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
      ButtonGroup(_.label := "Example Button Group")(
        Button()("Button"),
        Dropdown(
          _.slots.trigger(
            Button(_.withCaret := true)("Dropdown")
          )
        )(
          DropdownItem()("Item 1"),
          DropdownItem()("Item 2"),
          DropdownItem()("Item 3")
        ),
        Button()("Button")
      )
  })
}
  