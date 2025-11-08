package examples.webawesome.divider.h9c5600ba1863
  
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
        display.flex,
        alignItems.center,
        "First",
        Divider(
          _.orientation.vertical // [!code highlight]
        )(),
        "Middle",
        Divider(
          _.orientation.vertical // [!code highlight]
        )(),
        "Last"
      )
  })
}
  