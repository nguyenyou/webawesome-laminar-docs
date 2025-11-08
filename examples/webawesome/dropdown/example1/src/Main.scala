package examples.webawesome.dropdown.example1
  
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
          Button(_.withCaret := true)("View")
        )
      )(
        DropdownItem(
          _.slots.icon(Icon(_.name := "scissors")())
        )("Cut"),
        DropdownItem(
          _.slots.icon(Icon(_.name := "copy")())
        )("Copy"),
        DropdownItem(
          _.slots.icon(Icon(_.name := "paste")())
        )("Paste"),
        Divider()(),
        DropdownItem(
          _.slots.submenu(DropdownItem(_.value := "show-all-images")("Show All Images")),
          _.slots.submenu(DropdownItem(_.value := "show-thumbnails")("Show Thumbnails"))
        )("Show images"),
        Divider()(),
        DropdownItem(
          _.`type`.checkbox,
          _.checked := true
        )("Emoji Shortcuts"),
        DropdownItem(
          _.`type`.checkbox,
          _.checked := true
        )("Word Wrap"),
        Divider()(),
        DropdownItem(
          _.variant.danger,
          _.slots.icon(Icon(_.name := "trash")())
        )("Delete")
      )
  })
}
  