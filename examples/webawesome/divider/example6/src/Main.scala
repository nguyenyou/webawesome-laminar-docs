package examples.webawesome.divider.example6
  
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
      Dropdown(
        _.slots.trigger(
          Button(
            _.withCaret := true
          )("Menu")
        ),
        _.style := "max-width: 200px;" // [!code highlight]
      )(
        DropdownItem(
          _.value := "1"
        )("Option 1"),
        DropdownItem(
          _.value := "2"
        )("Option 2"),
        DropdownItem(
          _.value := "3"
        )("Option 3"),
        Divider()(), // [!code highlight]
        DropdownItem(
          _.value := "4"
        )("Option 4"),
        DropdownItem(
          _.value := "5"
        )("Option 5"),
        DropdownItem(
          _.value := "6"
        )("Option 6")
      )
  })
}
  