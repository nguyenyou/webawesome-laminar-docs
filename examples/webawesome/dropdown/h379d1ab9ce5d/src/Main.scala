package examples.webawesome.dropdown.h379d1ab9ce5d
  
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
          Button(_.withCaret := true)("Payment method")
        )
      )(
        DropdownItem(_.value := "cash")("Cash"),
        DropdownItem(_.value := "check", _.disabled := true)("Personal check"),
        DropdownItem(_.value := "credit")("Credit card"),
        DropdownItem(_.value := "gift-card")("Gift card")
      )
  })
}
  