package examples.webawesome.drawer.example12
  
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
        Drawer(
          _.id           := "drawer-light-dismiss",
          _.lightDismiss := true,
          _.label        := "Drawer",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.drawer
            )("Close")
          )
        )("This drawer closes when the user clicks on the overlay."),
        Button(
          _.open.drawer("drawer-light-dismiss")
        )("Open Drawer")
      )
  })
}
  