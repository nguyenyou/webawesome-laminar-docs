package examples.webawesome.icon.h0e073e9850a6
  
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
      Icon(
        _.name := "eyedropper"
      )()
      Icon(
        _.name := "grip-vertical"
      )()
      Icon(
        _.name := "play"
      )()
      Icon(
        _.name := "star"
      )()
      Icon(
        _.name := "user"
      )()
  })
}
  