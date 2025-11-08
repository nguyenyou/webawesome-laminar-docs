package examples.webawesome.`split-panel`.h4ea721e50b85
  
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
            _.orientation.vertical,
            _.style := "height: 400px;",
            _.slots.start(
              div(
                background := "var(--wa-color-surface-lowered)",
                tw.hFull.flex.itemsCenter.justifyCenter.overflowHidden,
                "Start"
              )
            ),
            _.slots.end(
              div(
                background := "var(--wa-color-surface-lowered)",
                tw.hFull.flex.itemsCenter.justifyCenter.overflowHidden,
                "End"
              )
            )
          )(),
        )
      )
  })
}
  