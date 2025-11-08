package examples.webawesome.slider.example4
  
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
      Slider(
        _.label       := "Quality",
        _.name        := "quality",
        _.withTooltip := true,
        _.hint        := "Controls the volume of the current song.",
        _.min         := 0,
        _.max         := 100,
        _.value       := "50"
      )()
  })
}
  