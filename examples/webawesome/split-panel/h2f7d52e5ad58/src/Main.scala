package examples.webawesome.split-panel.h2f7d52e5ad58
  
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
            _.positionInPixels := 150,
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
  