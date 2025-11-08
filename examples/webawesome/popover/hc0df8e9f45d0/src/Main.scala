package examples.webawesome.popover.hc0df8e9f45d0
  
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
          _.forId := "popover__autofocus"
        )(
          div(
            cls("flex flex-col gap-4"),
            Textarea(
              _.autofocus   := true,
              _.placeholder := "What's on your mind?",
              _.size.small,
              _.resize := "none",
              _.rows   := 3
            )(),
            Button(
              _.variant.brand,
              _.size.small,
              _.close.popover
            )("Submit")
          )
        ),
        Button(
          _.id := "popover__autofocus",
          _.slots.start(
            Icon(
              _.name := "comment"
            )()
          )
        )(
          "Feedback"
        )
      )
  })
}
  