package examples.webawesome.drawer.hc0c9b2394a07
  
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
              _.id := "drawer-placement-top",
              _.placement.top,
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("This drawer slides in from the top."),
            Button(
              _.open.drawer("drawer-placement-top")
            )("Open Drawer")
          ),
        )
      )
  })
}
  