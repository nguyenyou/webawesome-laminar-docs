package examples.webawesome.drawer.h690c8194f753
  
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
          val closeDrawerButton = Button(
            _.variant.brand,
            _.close.drawer
          )("Only this button will close it"),
        ),
        Examples(
          div(
            Drawer(
              _.id    := "drawer-deny-close",
              _.label := "Drawer",
              _.onHide.map { event =>
                if (event.detail.source != closeDrawerButton.ref) {
                  event.preventDefault()
                }
              } --> Observer.empty,
              _.slots.footer(
                closeDrawerButton
              )
            )("This drawer will only close when you click the button below."),
            Button(
              _.open.drawer("drawer-deny-close")
            )("Open Drawer")
          ),
        )
      )
  })
}
  