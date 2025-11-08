package examples.webawesome.drawer.h60c8214a1ff4
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      val openEvent = EventBus[Boolean]()
      
      div(
        Drawer(
          _.open <-- openEvent,
          _.label := "Drawer",
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
      )
  })
}
  