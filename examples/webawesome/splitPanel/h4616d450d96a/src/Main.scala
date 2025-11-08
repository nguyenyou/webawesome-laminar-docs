package examples.webawesome.splitPanel.h4616d450d96a
  
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
        cls("split-panel-snapping"),
        styleTag("""
          .split-panel-snapping {
            position: relative;
          }
      
          .split-panel-snapping-dots::before,
          .split-panel-snapping-dots::after {
            content: '';
            position: absolute;
            bottom: -12px;
            width: 6px;
            height: 6px;
            border-radius: 50%;
            background: var(--wa-color-neutral-fill-loud);
            transform: translateX(-3px);
          }
      
          .split-panel-snapping-dots::before {
            left: 100px;
          }
      
          .split-panel-snapping-dots::after {
            left: 50%;
          }
        """),
        SplitPanel(
          _.snap := "100px 50%",
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
        )(),
        div(cls("split-panel-snapping-dots"))
      )
  })
}
  