package examples.webawesome.slider.example8
  
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
        cls("flex gap-4"),
        Slider(
          _.label := "Volume",
          _.name  := "volume",
          _.orientation.vertical,
          _.value := "65",
          _.style := "width: 80px"
        )(),
        Slider(
          _.label := "Bass",
          _.name  := "bass",
          _.value := "50",
          _.orientation.vertical,
          _.style := "width: 80px"
        )(),
        Slider(
          _.label := "Treble",
          _.name  := "treble",
          _.value := "40",
          _.orientation.vertical,
          _.style := "width: 80px"
        )()
      )
  })
}
  