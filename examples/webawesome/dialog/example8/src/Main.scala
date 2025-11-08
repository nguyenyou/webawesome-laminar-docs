package examples.webawesome.dialog.example8
  
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
          _.id    := "dialog-header-actions",
          _.label := "Dialog",
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
              _.close.dialog
            )("Close")
          )
        )(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        Button(
          _.open.dialog("dialog-header-actions")
        )("Open Dialog")
      )
  })
}
  