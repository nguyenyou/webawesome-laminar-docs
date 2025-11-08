package examples.webawesome.card.example3
  
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
      Card(
        _.slots.header(
          div(
            cls("flex justify-between items-center"),
            "Header Title",
            Button(
              _.appearance.plain,
              _.slots.start(Icon(_.name := "gear", _.variant := "solid", _.label := "Settings")())
            )()
          )
        )
      )(
        maxWidth.px(300),
        "This card has a header. You can put all sorts of things in it!"
      )
  })
}
  