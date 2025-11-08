package examples.webawesome.popup.hf1650fe39c31
  
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
          val placementVar      = Var[SharedTypes.Placement]("top"),
          val arrowPlacementVar = Var[PopupArrowPlacement]("anchor"),
          val arrowVar          = Var(true),
        ),
        Examples(
          div(
            Popup(
              _.placement <-- placementVar,
              _.arrow <-- arrowVar,
              _.arrowPlacement <-- arrowPlacementVar,
              _.distance := 8,
              _.active   := true,
              _.style    := "--arrow-color: var(--wa-color-brand-fill-loud)",
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
            div(
              tw.flex.flexWrap.gap4.itemsEnd,
              Select(
                _.label := "Placement",
                _.name  := "placement",
                _.value <-- placementVar,
                _.onInput.mapToValue.map(_.asInstanceOf[SharedTypes.Placement]) --> placementVar
              )(
                width := "160px",
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
              ),
              Select(
                _.label := "Arrow Placement",
                _.name  := "arrow-placement",
                _.value <-- arrowPlacementVar,
                _.onInput.mapToValue.map {
                  case p: PopupArrowPlacement =>
                    arrowPlacementVar.set(p)
                  case _ => ()
                } --> Observer.empty
              )(
                width := "160px",
                UOption(_.value := "anchor")("anchor"),
                UOption(_.value := "start")("start"),
                UOption(_.value := "end")("end"),
                UOption(_.value := "center")("center")
              )
            ),
            div(
              tw.flex.gap4.mt4,
              Switch(
                _.name := "arrow",
                _.checked <-- arrowVar,
                _.onInput.mapToChecked --> arrowVar
              )("Arrow")
            )
          ),
        )
      )
  })
}
  