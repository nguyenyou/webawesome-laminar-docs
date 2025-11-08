package examples.webawesome.drawer.h01e046dd4ccb
  
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
          val openEvent = EventBus[Boolean](),
        ),
        Examples(
          div(
            Drawer(
              _.open <-- openEvent,
              _.label         := "Drawer",
              _.withoutHeader := true,
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.drawer
                )("Close")
              )
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button()(
              onClick.mapTo(true) --> openEvent,
              "Open Drawer"
            )
          ),
        )
      )
  })
}
  