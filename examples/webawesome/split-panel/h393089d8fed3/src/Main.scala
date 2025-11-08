package examples.webawesome.split-panel.h393089d8fed3
  
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
            cls("split-panel-snapping"),
            styleTag("""
              .split-panel-snapping {
                position: relative;
              }
          
              .split-panel-snapping-dots::before,
              .split-panel-snapping-dots::after {
                content: '';
                position: absolute;
                bottom: -12px;
                width: 6px;
                height: 6px;
                border-radius: 50%;
                background: var(--wa-color-neutral-fill-loud);
                transform: translateX(-3px);
              }
          
              .split-panel-snapping-dots::before {
                left: 100px;
              }
          
              .split-panel-snapping-dots::after {
                left: 50%;
              }
            """),
            SplitPanel(
              _.snap := "100px 50%",
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
            div(cls("split-panel-snapping-dots"))
          ),
        )
      )
  })
}
  