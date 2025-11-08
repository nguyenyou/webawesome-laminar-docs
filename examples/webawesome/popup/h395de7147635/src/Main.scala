package examples.webawesome.popup.h395de7147635
  
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
          val activeVar = Var(true),
        ),
        Examples(
          div(
            Popup(
              _.placement.top,
              _.active <-- activeVar,
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
              _.checked <-- activeVar,
              _.onInput.mapToChecked --> activeVar
            )("Active")
          ),
        )
      )
  })
}
  