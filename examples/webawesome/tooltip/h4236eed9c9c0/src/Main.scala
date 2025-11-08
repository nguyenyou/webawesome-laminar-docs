package examples.webawesome.tooltip.h4236eed9c9c0
  
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
          _.forId := "rich-tooltip",
          _.style := "--wa-tooltip-arrow-size: 0;"
        )(
          div(
            "I'm not ",
            strong("just"),
            " a tooltip, I'm a ",
            em("tooltip"),
            " with HTML!"
          )
        ),
        Button(_.id := "rich-tooltip")("Hover me")
      )
  })
}
  