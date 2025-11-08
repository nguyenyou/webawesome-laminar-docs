package examples.webawesome.popup.example13
  
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
      val enabledVar = Var(false)
      Var(0.0)
      Var(0.0)
      
      div(
        Popup(
          _.placement.rightStart,
          _.active <-- enabledVar,
          // Note: Setting virtual element would need to be done via ref in real implementation
          _.style := "z-index: 1000; pointer-events: none;"
        )(
          div(
            width.px(100),
            height.px(100),
            border       := "4px solid var(--wa-color-neutral-fill-loud)",
            borderRadius := "50%",
            transform    := "translate(-50px, -50px)",
            animation    := "1s virtual-cursor infinite"
          )
        ),
        Switch(
          _.checked <-- enabledVar,
          _.onInput.mapToChecked --> enabledVar
        )("Highlight mouse cursor"),
        // Note: Mouse tracking would need to be implemented via onMouseMove
        styleTag("""
          @keyframes virtual-cursor {
            0% { transform: translate(-50px, -50px) scale(1); }
            50% { transform: translate(-50px, -50px) scale(1.1); }
          }
        """)
      )
  })
}
  