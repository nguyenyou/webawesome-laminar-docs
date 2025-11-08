package examples.webawesome.popup.h972731a703d2
  
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
          val flipVar = Var(true),
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
          ),
        )
      )
  })
}
  