package examples.webawesome.popup.h646ca988a761
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      val placementVar = Var[SharedTypes.Placement]("top")
      val distanceVar  = Var("0")
      val skiddingVar  = Var("0")
      val activeVar    = Var(true)
      val arrowVar     = Var(false)
      
      div(
        padding.px(40),
        Popup(
          _.placement <-- placementVar,
          _.distance <-- distanceVar.signal.map(_.toDouble),
          _.skidding <-- skiddingVar.signal.map(_.toDouble),
          _.active <-- activeVar,
          _.arrow <-- arrowVar,
          _.style := "--arrow-color: var(--wa-color-brand-fill-loud)",
          _.slots.anchor(
            span(
              display.inlineBlock,
              width.px(150),
              height.px(150),
              border := "1px dashed var(--wa-color-neutral-fill-loud)",
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
          tw.flex.gap2,
          Select(
            _.label := "Placement",
            _.name  := "placement",
            _.value <-- placementVar,
            _.onInput.mapToValue.map(_.asInstanceOf[SharedTypes.Placement]) --> placementVar
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
          ),
          Input(
            _.label := "Distance",
            _.`type`.number,
            _.name := "distance",
            _.value <-- distanceVar,
            _.onInput.mapToValue --> distanceVar
          )(),
          Input(
            _.label := "Skidding",
            _.`type`.number,
            _.name := "skidding",
            _.value <-- skiddingVar,
            _.onInput.mapToValue --> skiddingVar
          )()
        ),
        div(
          tw.flex.gap2.mt2,
          Switch(
            _.name := "active",
            _.checked <-- activeVar,
            _.onInput.mapToChecked --> activeVar
          )("Active"),
          Switch(
            _.name := "arrow",
            _.checked <-- arrowVar,
            _.onInput.mapToChecked --> arrowVar
          )("Arrow")
        )
      )
  })
}
  