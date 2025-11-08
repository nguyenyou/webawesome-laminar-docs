package examples.webawesome.tooltip.h00656be5399f
  
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
          _.forId := "no-arrow",
          _.style := "--wa-tooltip-arrow-size: 0;"
        )("This is a tooltip with no arrow"),
        Button(_.id := "no-arrow")("No Arrow")
      )
  })
}
  