package examples.webawesome.tooltip.example2
  
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
        styleTag("""
          .tooltip-placement-example {
            width: 250px;
            margin: 1rem;
          }
      
          .tooltip-placement-example wa-button {
            width: 2.5rem;
          }
      
          .tooltip-placement-example-row {
            display: flex;
            justify-content: space-between;
            gap: 0.5rem;
            margin-bottom: 0.5rem;
          }
      
          .tooltip-placement-example-row:nth-child(1),
          .tooltip-placement-example-row:nth-child(5) {
            justify-content: center;
          }
        """),
        div(
          cls("tooltip-placement-example"),
          div(
            cls("tooltip-placement-example-row"),
            Button(_.id := "tooltip-top-start")(),
            Button(_.id := "tooltip-top")(),
            Button(_.id := "tooltip-top-end")()
          ),
          div(
            cls("tooltip-placement-example-row"),
            Button(_.id := "tooltip-left-start")(),
            Button(_.id := "tooltip-right-start")()
          ),
          div(
            cls("tooltip-placement-example-row"),
            Button(_.id := "tooltip-left")(),
            Button(_.id := "tooltip-right")()
          ),
          div(
            cls("tooltip-placement-example-row"),
            Button(_.id := "tooltip-left-end")(),
            Button(_.id := "tooltip-right-end")()
          ),
          div(
            cls("tooltip-placement-example-row"),
            Button(_.id := "tooltip-bottom-start")(),
            Button(_.id := "tooltip-bottom")(),
            Button(_.id := "tooltip-bottom-end")()
          )
        ),
        Tooltip(_.forId := "tooltip-top-start", _.placement.topStart)("top-start"),
        Tooltip(_.forId := "tooltip-top", _.placement.top)("top"),
        Tooltip(_.forId := "tooltip-top-end", _.placement.topEnd)("top-end"),
        Tooltip(_.forId := "tooltip-left-start", _.placement.leftStart)("left-start"),
        Tooltip(_.forId := "tooltip-right-start", _.placement.rightStart)("right-start"),
        Tooltip(_.forId := "tooltip-left", _.placement.left)("left"),
        Tooltip(_.forId := "tooltip-right", _.placement.right)("right"),
        Tooltip(_.forId := "tooltip-left-end", _.placement.leftEnd)("left-end"),
        Tooltip(_.forId := "tooltip-right-end", _.placement.rightEnd)("right-end"),
        Tooltip(_.forId := "tooltip-bottom-start", _.placement.bottomStart)("bottom-start"),
        Tooltip(_.forId := "tooltip-bottom", _.placement.bottom)("bottom"),
        Tooltip(_.forId := "tooltip-bottom-end", _.placement.bottomEnd)("bottom-end")
      )
  })
}
  