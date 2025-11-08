package examples.webawesome.relativeTime.example3
  
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
      div("Narrow: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.narrow)())
      div("Short: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.short)())
      div("Long: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.long)())
  })
}
  