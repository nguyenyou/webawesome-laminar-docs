package examples.webawesome.drawer.hd43a6ca5f3dc
  
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
              _.id    := "drawer-autofocus",
              _.label := "Drawer",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )(
              Input(
                _.autofocus   := true,
                _.placeholder := "I will have focus when the drawer is opened"
              )()
            ),
            Button(
              _.open.drawer("drawer-autofocus")
            )("Open Drawer")
          ),
        )
      )
  })
}
  