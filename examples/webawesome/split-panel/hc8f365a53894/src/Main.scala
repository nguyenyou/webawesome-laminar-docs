package examples.webawesome.split-panel.hc8f365a53894
  
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
          SplitPanel(
            _.style := "--divider-width: 20px;",
            _.slots.divider(
              Icon(
                _.name := "grip-vertical"
              )()
            ),
            _.slots.start(
              div(
                height.px(200),
                background := "var(--wa-color-surface-lowered)",
                tw.flex.itemsCenter.justifyCenter.overflowHidden,
                "Start"
              )
            ),
            _.slots.end(
              div(
                height.px(200),
                background := "var(--wa-color-surface-lowered)",
                tw.flex.itemsCenter.justifyCenter.overflowHidden,
                "End"
              )
            )
          )(),
        )
      )
  })
}
  