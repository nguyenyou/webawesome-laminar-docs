package examples.webawesome.dropdown.h61f6b479ac18
  
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
        _.onSelect.map(_.detail.item.value.toOption) --> Observer[Option[String]](println),
        _.slots.trigger(
          Button(_.withCaret := true)("View")
        )
      )(
        DropdownItem(_.value := "full-screen")("Enter full screen"),
        DropdownItem(_.value := "actual")("Actual size"),
        DropdownItem(_.value := "zoom-in")("Zoom in"),
        DropdownItem(_.value := "zoom-out")("Zoom out")
      )
  })
}
  