package examples.webawesome.dropdown.h0a58a9157b81
  
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
        _.skidding := 30.0,
        _.slots.trigger(
          Button(_.withCaret := true)("Edit")
        )
      )(
        DropdownItem()("Cut"),
        DropdownItem()("Copy"),
        DropdownItem()("Paste"),
        Divider()(),
        DropdownItem()("Find"),
        DropdownItem()("Replace")
      )
  })
}
  