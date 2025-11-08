package examples.webawesome.dropdown.example5
  
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
          Button(_.withCaret := true)("Message")
        )
      )(
        DropdownItem(
          _.value := "reply",
          _.slots.details(span("⌘R"))
        )("Reply"),
        DropdownItem(
          _.value := "forward",
          _.slots.details(span("⌘F"))
        )("Forward"),
        DropdownItem(
          _.value := "move",
          _.slots.details(span("⌘M"))
        )("Move"),
        Divider()(),
        DropdownItem(
          _.value := "archive",
          _.slots.details(span("⌘A"))
        )("Archive"),
        DropdownItem(
          _.value := "delete",
          _.slots.details(span("Del"))
        )("Delete")
      )
  })
}
  