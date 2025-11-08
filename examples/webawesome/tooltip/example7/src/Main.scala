package examples.webawesome.tooltip.example7
  
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
        Tooltip(
          _.forId := "wrapping-tooltip",
          _.style := "--max-width: 80px;"
        )(
          "This tooltip will wrap after only 80 pixels."
        ),
        Button(_.id := "wrapping-tooltip")("Hover me")
      )
  })
}
  