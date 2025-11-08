package examples.webawesome.slider.h832b803e95d1
  
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
      val valueVar = Var(3.0)
      
      Slider(
        _.label := "Number of cats",
        _.hint  := "Limit six per household",
        _.name  := "value",
        _.value <-- valueVar.signal.map(_.toString),
        _.onInput.map(_.target.value) --> valueVar,
        _.min         := 0,
        _.max         := 6,
        _.withMarkers := true,
        _.withTooltip := true,
        _.slots.reference(span("Less")),
        _.slots.reference(span("More"))
      )()
  })
}
  