package examples.webawesome.buttonGroup.example8
  
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
        Button(_.variant.brand)("Save"),
        Dropdown(
          _.placement.bottomEnd,
          _.slots.trigger(
            Button(_.variant.brand)(
              Icon(_.name := "chevron-down", _.label := "More options")() // [!code highlight]
            )
          )
        )(
          DropdownItem()("Save"),
          DropdownItem()("Save as…"),
          DropdownItem()("Save all")
        )
      )
  })
}
  