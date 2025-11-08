package examples.webawesome.relativeTime.hbb58930a1253
  
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
      div("English: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "en-US"))
      div("Chinese: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "zh-CN"))
      div("German: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "de"))
      div("Greek: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "el"))
      div("Russian: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "ru"))
  })
}
  