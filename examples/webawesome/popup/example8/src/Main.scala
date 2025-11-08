package examples.webawesome.popup.example8
  
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
      val flipVar = Var(true)
      
      div(
        div(
          position.relative,
          height.px(300),
          border := "2px solid var(--wa-color-surface-border)",
          overflow.auto,
          Popup(
            _.placement.top,
            _.flip <-- flipVar,
            _.active := true,
            _.boundary.scroll,
            _.slots.anchor(
              span(
                display.inlineBlock,
                width.px(150),
                height.px(150),
                border := "2px dashed var(--wa-color-neutral-fill-loud)",
                margin := "150px 50px"
              )
            )
          )(
            div(
              width.px(100),
              height.px(50),
              background   := "var(--wa-color-brand-fill-loud)",
              borderRadius := "var(--wa-border-radius-m)"
            )
          )
        ),
        br(),
        Switch(
          _.checked <-- flipVar,
          _.onInput.mapToChecked --> flipVar
        )("Flip")
      )
  })
}
  