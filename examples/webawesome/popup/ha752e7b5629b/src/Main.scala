package examples.webawesome.popup.ha752e7b5629b
  
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
      val hoverBridgeVar = Var(true)
      val distanceVar    = Var(10.0)
      val skiddingVar    = Var(0.0)
      
      div(
        Popup(
          _.placement.top,
          _.hoverBridge <-- hoverBridgeVar,
          _.distance <-- distanceVar,
          _.skidding <-- skiddingVar,
          _.active := true,
          _.style := """
            --arrow-color: var(--wa-color-brand-fill-loud);
          """.stripMargin.trim,
          _.slots.anchor(
            span(
              display.inlineBlock,
              width.px(150),
              height.px(150),
              border := "2px dashed var(--wa-color-neutral-fill-loud)",
              margin.px(50)
            )
          )
        )(
          div(
            width.px(100),
            height.px(50),
            background   := "var(--wa-color-brand-fill-loud)",
            borderRadius := "var(--wa-border-radius-m)"
          )
        ),
        br(),
        Switch(
          _.checked <-- hoverBridgeVar,
          _.onInput.mapToChecked --> hoverBridgeVar
        )("Hover Bridge"),
        br(),
        Slider(
          _.label := "Distance",
          _.min   := 0,
          _.max   := 50,
          _.step  := 1,
          _.value <-- distanceVar.signal.map(_.toString),
          _.onInput.map(_.target.value) --> distanceVar
        )(
          maxWidth  := "260px",
          marginTop := "0.5rem"
        ),
        Slider(
          _.label := "Skidding",
          _.min   := -50,
          _.max   := 50,
          _.step  := 1,
          _.value <-- skiddingVar.signal.map(_.toString),
          _.onInput.map(_.target.value) --> skiddingVar
        )(
          maxWidth  := "260px",
          marginTop := "0.5rem"
        )
      )
  })
}
  