package examples.webawesome.popover.hb61948138502
  
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
            _.id := "popover__big-arrow"
          )("Big arrow"),
          Popover(
            _.forId := "popover__big-arrow",
            _.style := "--arrow-size: 8px;"
          )("I have a big arrow")
        ),
        div(
          Button(
            _.id := "popover__no-arrow"
          )("No arrow"),
          Popover(
            _.forId := "popover__no-arrow",
            _.style := "--arrow-size: 0;"
          )("I don't have an arrow")
        )
      )
  })
}
  