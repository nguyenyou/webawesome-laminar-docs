package examples.webawesome.drawer.hba5db8321e4e
  
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
              _.id := "drawer-placement-start",
              _.placement.start,
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("This drawer slides in from the start."),
            Button(
              _.open.drawer("drawer-placement-start")
            )("Open Drawer")
          ),
        )
      )
  })
}
  