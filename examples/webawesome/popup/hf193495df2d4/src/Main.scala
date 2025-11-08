package examples.webawesome.popup.hf193495df2d4
  
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
          val autoSizeVar = Var(true),
        ),
        Examples(
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
          ),
        )
      )
  })
}
  