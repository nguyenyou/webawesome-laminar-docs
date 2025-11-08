package examples.webawesome.drawer.hee03c02249c0
  
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
              _.id           := "drawer-light-dismiss",
              _.lightDismiss := true,
              _.label        := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("This drawer closes when the user clicks on the overlay."),
            Button(
              _.open.drawer("drawer-light-dismiss")
            )("Open Drawer")
          ),
        )
      )
  })
}
  