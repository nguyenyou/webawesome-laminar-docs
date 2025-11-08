package examples.webawesome.popup.example11
  
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
      val autoSizeVar = Var(true)
      
      div(
        div(
          position.relative,
          height.px(300),
          border := "2px solid var(--wa-color-surface-border)",
          overflow.auto,
          Popup(
            _.placement.top,
            _.autoSize <-- autoSizeVar.signal.map(if (_) "both" else "vertical"),
            _.autoSizePadding := 10,
            _.active          := true,
            _.boundary.scroll,
            _.slots.anchor(
              span(
                display.inlineBlock,
                width.px(150),
                height.px(150),
                border := "2px dashed var(--wa-color-neutral-fill-loud)",
                margin := "250px 50px 100px 50px"
              )
            )
          )(
            div(
              background   := "var(--wa-color-brand-fill-loud)",
              borderRadius := "var(--wa-border-radius-m)",
              width.px(100),
              height.px(200),
              maxWidth  := "var(--auto-size-available-width)",
              maxHeight := "var(--auto-size-available-height)",
              overflow.auto
            )
          )
        ),
        br(),
        Switch(
          _.checked <-- autoSizeVar,
          _.onInput.mapToChecked --> autoSizeVar
        )("Auto-size")
      )
  })
}
  