package examples.webawesome.popup.hdb7fdfdfbeec
  
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
          val skiddingVar = Var(0.0),
        ),
        Examples(
          div(
            Popup(
              _.placement.top,
              _.skidding <-- skiddingVar,
              _.active := true,
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
            Slider(
              _.label := "Skidding",
              _.min   := -50,
              _.max   := 50,
              _.step  := 1,
              _.value <-- skiddingVar.signal.map(_.toString),
              _.onInput.map(_.target.value) --> skiddingVar
            )(
              maxWidth := "260px"
            )
          ),
        )
      )
  })
}
  