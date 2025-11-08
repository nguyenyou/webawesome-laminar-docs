package examples.webawesome.drawer.hb5f94d7b929e
  
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
              _.id    := "drawer-custom-size",
              _.label := "Drawer",
              _.style := "--size: 50%",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )(
              "This drawer is always 50% of the viewport."
            ),
            Button(
              _.open.drawer("drawer-custom-size")
            )("Open Drawer")
          ),
        )
      )
  })
}
  