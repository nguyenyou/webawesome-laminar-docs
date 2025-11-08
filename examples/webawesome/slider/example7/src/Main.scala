package examples.webawesome.slider.example7
  
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
        _.label       := "Speed",
        _.name        := "speed",
        _.withMarkers := true,
        _.min         := 1,
        _.max         := 5,
        _.value       := "3",
        _.hint        := "Controls the speed of the thing you're currently doing.",
        _.slots.reference(span("Slow")),
        _.slots.reference(span("Medium")),
        _.slots.reference(span("Fast"))
      )()
  })
}
  