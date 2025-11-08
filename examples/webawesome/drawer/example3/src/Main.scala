package examples.webawesome.drawer.example3
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

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
  