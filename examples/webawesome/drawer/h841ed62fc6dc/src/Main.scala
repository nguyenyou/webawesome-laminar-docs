package examples.webawesome.drawer.h841ed62fc6dc
  
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
              _.id := "drawer-placement-bottom",
              _.placement.bottom,
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("This drawer slides in from the bottom."),
            Button(
              _.open.drawer("drawer-placement-bottom")
            )("Open Drawer")
          ),
        )
      )
  })
}
  