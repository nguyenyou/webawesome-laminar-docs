package examples.webawesome.drawer.hcba7d6b4f9b7
  
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
              _.id    := "drawer-dismiss",
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button(
              _.open.drawer("drawer-dismiss")
            )(
              "Open Drawer"
            )
          ),
        )
      )
  })
}
  