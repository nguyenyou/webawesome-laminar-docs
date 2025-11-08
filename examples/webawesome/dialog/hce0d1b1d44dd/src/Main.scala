package examples.webawesome.dialog.hce0d1b1d44dd
  
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
              _.id    := "dialog-opening", // [!code highlight]
              _.label := "Dialog"
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button(
              _.open.dialog("dialog-opening") // [!code highlight]
            )("Open Dialog")
          ),
        )
      )
  })
}
  