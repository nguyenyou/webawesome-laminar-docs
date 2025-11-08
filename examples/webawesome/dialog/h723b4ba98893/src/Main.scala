package examples.webawesome.dialog.h723b4ba98893
  
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
              _.id    := "dialog-dismiss", // [!code highlight]
              _.label := "Dialog",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog // [!code highlight]
                )("Close")
              )
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button(
              _.open.dialog("dialog-dismiss") // [!code highlight]
            )("Open Dialog")
          ),
        )
      )
  })
}
  