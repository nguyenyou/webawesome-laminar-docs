package examples.webawesome.dialog.example9
  
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
      div(
        Dialog(
          _.id           := "dialog-light-dismiss",
          _.label        := "Dialog",
          _.lightDismiss := true, // [!code highlight]
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.dialog
            )("Close")
          )
        )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
        Button(
          _.open.dialog("dialog-light-dismiss")
        )("Open Dialog")
      )
  })
}
  