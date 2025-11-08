package examples.webawesome.popover.example3
  
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
        Popover(
          _.forId := "popover__opening"
        )(
          div(
            cls("flex flex-col gap-4"),
            "Click the button below to close the popover",
            Button(
              _.variant.brand,
              _.close.popover
            )("Dismiss")
          )
        ),
        Button(
          _.id := "popover__opening"
        )("Show popover")
      )
  })
}
  