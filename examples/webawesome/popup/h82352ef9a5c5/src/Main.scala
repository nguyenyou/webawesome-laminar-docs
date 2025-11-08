package examples.webawesome.popup.h82352ef9a5c5
  
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
            span(
              idAttr := "external-anchor",
              display.inlineBlock,
              width.px(150),
              height.px(150),
              border := "2px dashed var(--wa-color-neutral-fill-loud)",
              margin := "50px 0 0 50px"
            ),
            Popup(
              _.anchor := "external-anchor",
              _.placement.top,
              _.active := true
            )(
              div(
                width.px(100),
                height.px(50),
                background   := "var(--wa-color-brand-fill-loud)",
                borderRadius := "var(--wa-border-radius-m)"
              )
            )
          ),
        )
      )
  })
}
  