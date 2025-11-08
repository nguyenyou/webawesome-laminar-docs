package examples.webawesome.drawer.hf8d02a2e90f4
  
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
          _.id    := "drawer-scrolling",
          _.label := "Drawer",
          _.slots.footer(
            Button(
              _.variant.brand,
              _.close.drawer
            )("Close")
          )
        )(
          div(
            height.vh(150),
            border  := "2px dashed var(--wa-color-surface-border)",
            padding := "0 1rem",
            p(
              "Scroll down and give it a try! 👇"
            )
          )
        ),
        Button(
          _.open.drawer("drawer-scrolling")
        )("Open Drawer")
      )
  })
}
  