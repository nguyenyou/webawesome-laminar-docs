package examples.webawesome.popup.h97c1881490ff
  
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
          div(
            div(
              position.relative,
              height.px(300),
              border := "2px solid var(--wa-color-surface-border)",
              overflow.auto,
              Popup(
                _.placement.top,
                _.flip                   := true,
                _.flipFallbackPlacements := "right bottom",
                _.flipFallbackStrategy.initial,
                _.active := true,
                _.boundary.scroll,
                _.slots.anchor(
                  span(
                    display.inlineBlock,
                    width.px(150),
                    height.px(150),
                    border := "2px dashed var(--wa-color-neutral-fill-loud)",
                    margin := "250px 50px"
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
            )
          ),
        )
      )
  })
}
  