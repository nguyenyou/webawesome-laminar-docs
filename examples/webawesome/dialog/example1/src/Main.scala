package examples.webawesome.dialog.example1
  
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
        Dialog(
          _.open <-- openEvent,
          _.label := "Dialog",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.dialog
            )("Close")
          )
        )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
        Button()(
          onClick.mapTo(true) --> openEvent,
          "Open Dialog"
        )
      )
  })
}
  