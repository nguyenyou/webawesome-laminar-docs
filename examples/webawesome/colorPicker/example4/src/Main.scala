package examples.webawesome.colorPicker.example4
  
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
      ColorPicker(
        _.format.hex,
        _.value := "#4a90e2",
        _.label := "Pick a hex color"
      )()
      ColorPicker(
        _.format.rgb,
        _.value := "rgb(80, 227, 194)",
        _.label := "Pick an RGB color"
      )()
      ColorPicker(
        _.format.hsl,
        _.value := "hsl(290, 87%, 47%)",
        _.label := "Pick an HSL color"
      )()
      ColorPicker(
        _.format.hsv,
        _.value := "hsv(55, 89%, 97%)",
        _.label := "Pick an HSV color"
      )()
  })
}
  