package examples.webawesome.popup.h3ddc6a1c04a7
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ExampleGroups(
        Examples(
          val shiftVar = Var(true),
        ),
        Examples(
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
          ),
        )
      )
  })
}
  