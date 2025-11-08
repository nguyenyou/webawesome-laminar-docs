package examples.webawesome.slider.h3339a08cb5cd
  
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
        _.label           := "User Friendliness",
        _.hint            := "Did you find our product easy to use?",
        _.name            := "value",
        _.value           := "0",
        _.min             := -5,
        _.max             := 5,
        _.indicatorOffset := 0,
        _.withMarkers     := true,
        _.withTooltip     := true,
        _.slots.reference(span("Easy")),
        _.slots.reference(span("Moderate")),
        _.slots.reference(span("Difficult"))
      )()
  })
}
  