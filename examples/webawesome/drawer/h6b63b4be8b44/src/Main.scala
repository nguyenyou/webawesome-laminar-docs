package examples.webawesome.drawer.h6b63b4be8b44
  
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
          _.id    := "drawer-header-actions",
          _.label := "Drawer",
          _.slots.headerActions(
            Button(
              _.appearance.plain
            )(
              onClick --> Observer { _ =>
                window.open(window.location.href)
              },
              Icon(
                _.name  := "arrow-up-right-from-square",
                _.label := "Open in new window"
              )()
            )
          ),
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.drawer
            )("Close")
          )
        )(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        Button(
          _.open.drawer("drawer-header-actions")
        )("Open Drawer")
      )
  })
}
  