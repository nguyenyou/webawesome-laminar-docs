package examples.webawesome.splitPanel.h518d40dd1063
  
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
        _.positionInPixels := 150,
        _.slots.start(
          div(
            height.px(200),
            background := "var(--wa-color-surface-lowered)",
            cls("flex items-center justify-center overflow-hidden"),
            "Start"
          )
        ),
        _.slots.end(
          div(
            height.px(200),
            background := "var(--wa-color-surface-lowered)",
            cls("flex items-center justify-center overflow-hidden"),
            "End"
          )
        )
      )()
  })
}
  