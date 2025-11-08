package examples.webawesome.slider.hd42519fe3dc8
  
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
        _.label       := "Size",
        _.name        := "size",
        _.withMarkers := true,
        _.min         := 0,
        _.max         := 8,
        _.value       := "4"
      )()
  })
}
  