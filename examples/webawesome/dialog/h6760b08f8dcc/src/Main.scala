package examples.webawesome.dialog.h6760b08f8dcc
  
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
              _.id           := "dialog-light-dismiss",
              _.label        := "Dialog",
              _.lightDismiss := true, // [!code highlight]
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog
                )("Close")
              )
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button(
              _.open.dialog("dialog-light-dismiss")
            )("Open Dialog")
          ),
        )
      )
  })
}
  