package examples.webawesome.drawer.h01a6bddf9a05
  
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
      val closeDrawerButton = Button(
        _.variant.brand,
        _.close.drawer
      )("Only this button will close it")
      
      div(
        Drawer(
          _.id    := "drawer-deny-close",
          _.label := "Drawer",
          _.onHide.map { event =>
            if (event.detail.source != closeDrawerButton.ref) {
              event.preventDefault()
            }
          } --> Observer.empty,
          _.slots.footer(
            closeDrawerButton
          )
        )("This drawer will only close when you click the button below."),
        Button(
          _.open.drawer("drawer-deny-close")
        )("Open Drawer")
      )
  })
}
  