package examples.webawesome.dialog.example11
  
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
          _.id    := "dialog-focus",
          _.label := "Dialog",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.dialog
            )("Close")
          )
        )(
          Input(
            _.autofocus   := true, // [!code highlight]
            _.placeholder := "I will have focus when the dialog is opened"
          )()
        ),
        Button(
          _.open.dialog("dialog-focus")
        )("Open Dialog")
      )
  })
}
  