package examples.webawesome.slider.example9
  
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
        _.label := "Small",
        _.size.small,
        _.value := "50"
      )()
      Slider(
        _.label := "Medium",
        _.size.medium,
        _.value := "50"
      )()
      Slider(
        _.label := "Large",
        _.size.large,
        _.value := "50"
      )()
  })
}
  