package examples.webawesome.dialog.h4342c0ca5a1c
  
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
              _.id    := "dialog-custom-width",
              _.label := "Dialog",
              _.style := "--width: 50vw;", // [!code highlight]
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog
                )("Close")
              )
            )(
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            ),
            Button(
              _.open.dialog("dialog-custom-width")
            )("Open Dialog")
          ),
        )
      )
  })
}
  