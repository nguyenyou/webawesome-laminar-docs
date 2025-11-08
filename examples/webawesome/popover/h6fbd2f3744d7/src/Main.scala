package examples.webawesome.popover.h6fbd2f3744d7
  
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
      div(
        cls("flex gap-4 items-center"),
        div(
          Button(
            _.id := "popover__distance-near"
          )("Near"),
          Popover(
            _.forId    := "popover__distance-near",
            _.distance := 0
          )("I'm very close")
        ),
        div(
          Button(
            _.id := "popover__distance-far"
          )("Far"),
          Popover(
            _.forId    := "popover__distance-far",
            _.distance := 30
          )("I'm farther away")
        )
      )
  })
}
  