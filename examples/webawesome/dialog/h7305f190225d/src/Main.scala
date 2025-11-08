package examples.webawesome.dialog.h7305f190225d
  
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
          val closeDialogButton = Button(
            _.variant.brand,
            _.close.dialog
          )("Only this button will close it"),
        ),
        Examples(
          div(
            Dialog(
              _.id    := "dialog-deny-close",
              _.label := "Dialog",
              _.onHide.map { event =>
                if (event.detail.source != closeDialogButton.ref) {
                  event.preventDefault()
                }
              } --> Observer.empty,
              _.slots.footer(
                closeDialogButton
              )
            )("This dialog will only close when you click the button below."),
            Button(
              _.open.dialog("dialog-deny-close")
            )("Open Dialog")
          ),
        )
      )
  })
}
  