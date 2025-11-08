package examples.webawesome.dropdown.h0841c2549943
  
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
          Button(_.withCaret := true)("Edit")
        )
      )(
        DropdownItem(_.value := "cut", _.slots.icon(Icon(_.name := "scissors")()))("Cut"),
        DropdownItem(_.value := "copy", _.slots.icon(Icon(_.name := "copy")()))("Copy"),
        DropdownItem(_.value := "paste", _.slots.icon(Icon(_.name := "paste")()))("Paste"),
        DropdownItem(_.value := "delete", _.slots.icon(Icon(_.name := "trash")()))("Delete")
      )
  })
}
  