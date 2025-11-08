package examples.webawesome.popover.h71fd14416ac6
  
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
            _.id := "popover__top"
          )("Top"),
          Popover(
            _.forId     := "popover__top",
            _.placement := "top"
          )("I'm on the top")
        ),
        div(
          Button(
            _.id := "popover__bottom"
          )("Bottom"),
          Popover(
            _.forId     := "popover__bottom",
            _.placement := "bottom"
          )("I'm on the bottom")
        ),
        div(
          Button(
            _.id := "popover__left"
          )("Left"),
          Popover(
            _.forId     := "popover__left",
            _.placement := "left"
          )("I'm on the left")
        ),
        div(
          Button(
            _.id := "popover__right"
          )("Right"),
          Popover(
            _.forId     := "popover__right",
            _.placement := "right"
          )("I'm on the right")
        )
      )
  })
}
  