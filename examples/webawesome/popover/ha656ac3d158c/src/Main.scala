package examples.webawesome.popover.ha656ac3d158c
  
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
        cls("flex flex-col items-start gap-4"),
        div(
          Popover(
            _.forId := "popover__anchor-button"
          )(
            "I'm anchored to a Web Awesome button."
          ),
          Button(
            _.id := "popover__anchor-button"
          )("Show popover")
        ),
        div(
          Popover(
            _.forId := "popover__anchor-native-button"
          )(
            "I'm anchored to a native button."
          ),
          button(
            idAttr := "popover__anchor-native-button",
            "Show Popover"
          )
        )
      )
  })
}
  