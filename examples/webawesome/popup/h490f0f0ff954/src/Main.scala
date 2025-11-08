package examples.webawesome.popup.h490f0f0ff954
  
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
          val placementVar = Var[SharedTypes.Placement]("top"),
        ),
        Examples(
          div(
            Popup(
              _.placement <-- placementVar,
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
            Select(
              _.label := "Placement",
              _.value <-- placementVar,
              _.onInput.mapToValue.map(_.asInstanceOf[SharedTypes.Placement]) --> placementVar,
              _.style := "max-width: 280px;"
            )(
              UOption(_.value := CommonKeys.Placement.top.value)("top"),
              UOption(_.value := CommonKeys.Placement.topStart.value)("top-start"),
              UOption(_.value := CommonKeys.Placement.topEnd.value)("top-end"),
              UOption(_.value := CommonKeys.Placement.bottom.value)("bottom"),
              UOption(_.value := CommonKeys.Placement.bottomStart.value)("bottom-start"),
              UOption(_.value := CommonKeys.Placement.bottomEnd.value)("bottom-end"),
              UOption(_.value := CommonKeys.Placement.right.value)("right"),
              UOption(_.value := CommonKeys.Placement.rightStart.value)("right-start"),
              UOption(_.value := CommonKeys.Placement.rightEnd.value)("right-end"),
              UOption(_.value := CommonKeys.Placement.left.value)("left"),
              UOption(_.value := CommonKeys.Placement.leftStart.value)("left-start"),
              UOption(_.value := CommonKeys.Placement.leftEnd.value)("left-end")
            )
          ),
        )
      )
  })
}
  