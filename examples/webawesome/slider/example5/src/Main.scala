package examples.webawesome.slider.example5
  
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
        _.label       := "Between zero and one",
        _.hint        := "Controls the volume of the current song.",
        _.withTooltip := true,
        _.min         := 0,
        _.max         := 1,
        _.step        := 0.1,
        _.value       := "0.5"
      )()
  })
}
  