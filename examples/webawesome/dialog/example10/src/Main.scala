package examples.webawesome.dialog.example10
  
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
      val closeDialogButton = Button(
        _.variant.brand,
        _.close.dialog
      )("Only this button will close it")
      
      div(
        Dialog(
          _.id    := "dialog-deny-close",
          _.label := "Dialog",
          _.onHide.map { event =>
            if (event.detail.source != closeDialogButton.ref) {
              event.preventDefault()
            }
          } --> Observer.empty,
          _.slots.footer(
            closeDialogButton
          )
        )("This dialog will only close when you click the button below."),
        Button(
          _.open.dialog("dialog-deny-close")
        )("Open Dialog")
      )
  })
}
  