package examples.webawesome.popup.h649d3f330ec4
  
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
      val shiftVar = Var(true)
      
      div(
        div(
          position.relative,
          border := "2px solid var(--wa-color-surface-border)",
          overflow.auto,
          Popup(
            _.placement.top,
            _.shift <-- shiftVar,
            _.shiftPadding := 10,
            _.active       := true,
            _.boundary.scroll,
            _.slots.anchor(
              span(
                display.inlineBlock,
                width.px(150),
                height.px(150),
                border := "2px dashed var(--wa-color-neutral-fill-loud)",
                margin := "60px 0 0 10px"
              )
            )
          )(
            div(
              width.px(300),
              height.px(50),
              background   := "var(--wa-color-brand-fill-loud)",
              borderRadius := "var(--wa-border-radius-m)"
            )
          )
        ),
        Switch(
          _.checked <-- shiftVar,
          _.onInput.mapToChecked --> shiftVar
        )("Shift")
      )
  })
}
  