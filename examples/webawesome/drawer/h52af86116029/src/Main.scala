package examples.webawesome.drawer.h52af86116029
  
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
            Drawer(
              _.id    := "drawer-scrolling",
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )(
              div(
                height.vh(150),
                border  := "2px dashed var(--wa-color-surface-border)",
                padding := "0 1rem",
                p(
                  "Scroll down and give it a try! 👇"
                )
              )
            ),
            Button(
              _.open.drawer("drawer-scrolling")
            )("Open Drawer")
          ),
        )
      )
  })
}
  