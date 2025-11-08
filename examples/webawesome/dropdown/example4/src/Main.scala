package examples.webawesome.dropdown.example4
  
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
          Button(_.withCaret := true)("Device")
        )
      )(
        h3("Type"),
        DropdownItem(_.value := "phone")("Phone"),
        DropdownItem(_.value := "tablet")("Tablet"),
        DropdownItem(_.value := "desktop")("Desktop"),
        Divider()(),
        DropdownItem(_.value := "more")("More options...")
      )
  })
}
  