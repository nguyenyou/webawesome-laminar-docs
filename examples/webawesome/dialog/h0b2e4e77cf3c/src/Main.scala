package examples.webawesome.dialog.h0b2e4e77cf3c
  
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
              _.id    := "dialog-focus",
              _.label := "Dialog",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog
                )("Close")
              )
            )(
              Input(
                _.autofocus   := true, // [!code highlight]
                _.placeholder := "I will have focus when the dialog is opened"
              )()
            ),
            Button(
              _.open.dialog("dialog-focus")
            )("Open Dialog")
          ),
        )
      )
  })
}
  