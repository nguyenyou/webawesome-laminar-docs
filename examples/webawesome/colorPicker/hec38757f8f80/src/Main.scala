package examples.webawesome.colorPicker.hec38757f8f80
  
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
        _.size.small,
        _.label := "Select a color"
      )()
      ColorPicker(
        _.size.medium,
        _.label := "Select a color"
      )()
      ColorPicker(
        _.size.large,
        _.label := "Select a color"
      )()
  })
}
  