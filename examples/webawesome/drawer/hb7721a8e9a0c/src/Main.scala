package examples.webawesome.drawer.hb7721a8e9a0c
  
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
          _.id    := "drawer-custom-size",
          _.label := "Drawer",
          _.style := "--size: 50%",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.drawer
            )("Close")
          )
        )(
          "This drawer is always 50% of the viewport."
        ),
        Button(
          _.open.drawer("drawer-custom-size")
        )("Open Drawer")
      )
  })
}
  