package examples.webawesome.icon.h42d8e4a5e0e2
  
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
        _.fixedWidth := "true",
        _.name       := "cloud"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "user"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "truck"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "file"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "skating"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "snowplow"
      )()
  })
}
  