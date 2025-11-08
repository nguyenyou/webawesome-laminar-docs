package examples.webawesome.dropdown.hd4408e157e1f
  
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
        _.onSelect.map { event =>
          event.detail.item.`type`.toOption match {
            case Some("checkbox") =>
              println(event.detail.item.value.toOption)
              println(event.detail.item.checked.toOption)
            case _ =>
              println(event.detail.item.value.toOption)
          }
        } --> Observer.empty,
        _.slots.trigger(
          Button(_.withCaret := true)("View")
        )
      )(
        DropdownItem(_.`type`.checkbox, _.value := "canvas", _.checked := true)("Show canvas"),
        DropdownItem(_.`type`.checkbox, _.value := "grid", _.checked := true)("Show grid"),
        DropdownItem(_.`type`.checkbox, _.value := "source")("Show source"),
        Divider()(),
        DropdownItem(_.value := "preferences")("Preferences…")
      )
  })
}
  