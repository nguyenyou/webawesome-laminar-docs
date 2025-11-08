package examples.webawesome.drawer.example8
  
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
          _.id := "drawer-placement-bottom",
          _.placement.bottom,
          _.label := "Drawer",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.drawer
            )("Close")
          )
        )("This drawer slides in from the bottom."),
        Button(
          _.open.drawer("drawer-placement-bottom")
        )("Open Drawer")
      )
  })
}
  