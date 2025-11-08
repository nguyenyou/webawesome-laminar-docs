package examples.webawesome.splitPanel.h27c0525ca14a
  
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
      SplitPanel(
        _.slots.start(
          div(
            height.px(400),
            background := "var(--wa-color-surface-lowered)",
            cls("flex items-center justify-center overflow-hidden"),
            "Start"
          )
        ),
        _.slots.end(
          div(
            SplitPanel(
              _.orientation.vertical,
              _.style := "height: 400px;",
              _.slots.start(
                div(
                  background := "var(--wa-color-surface-lowered)",
                  cls("h-full flex items-center justify-center overflow-hidden"),
                  "Top"
                )
              ),
              _.slots.end(
                div(
                  background := "var(--wa-color-surface-lowered)",
                  cls("h-full flex items-center justify-center overflow-hidden"),
                  "Bottom"
                )
              )
            )()
          )
        )
      )()
  })
}
  