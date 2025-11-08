package examples.webawesome.dropdown.h5f4cf5e88767
  
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
          Button(_.withCaret := true)("Project")
        )
      )(
        DropdownItem(
          _.value := "share",
          _.slots.icon(
            Icon(_.name := "share")()
          )
        )("Share"),
        DropdownItem(
          _.value := "preferences",
          _.slots.icon(
            Icon(_.name := "gear")()
          )
        )("Preferences"),
        Divider()(),
        h3("Danger zone"),
        DropdownItem(
          _.value := "archive",
          _.slots.icon(
            Icon(_.name := "archive")()
          )
        )("Archive"),
        DropdownItem(
          _.value   := "delete",
          _.variant := "danger",
          _.slots.icon(
            Icon(_.name := "trash")()
          )
        )("Delete")
      )
  })
}
  