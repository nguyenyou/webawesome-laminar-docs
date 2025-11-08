package examples.webawesome.dialog.example5
  
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
          _.id    := "dialog-dismiss", // [!code highlight]
          _.label := "Dialog",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.dialog // [!code highlight]
            )("Close")
          )
        )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
        Button(
          _.open.dialog("dialog-dismiss") // [!code highlight]
        )("Open Dialog")
      )
  })
}
  