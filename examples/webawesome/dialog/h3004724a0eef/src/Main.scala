package examples.webawesome.dialog.h3004724a0eef
  
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
            Dialog(
              _.id    := "dialog-scrolling",
              _.label := "Dialog",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog
                )("Close")
              )
            )(
              div(
                height.vh(150),
                border  := "1px dashed var(--wa-color-surface-border)",
                padding := "0 1rem",
                p(
                  "Scroll down and give it a try! 👇"
                )
              )
            ),
            Button(
              _.open.dialog("dialog-scrolling")
            )("Open Dialog")
          ),
        )
      )
  })
}
  