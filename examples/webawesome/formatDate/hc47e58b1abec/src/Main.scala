package examples.webawesome.formatDate.hc47e58b1abec
  
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
      div("UTC: ", FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "UTC")())
      div(
        "Pacific: ",
        FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "America/Los_Angeles")()
      )
      div("Tokyo: ", FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "Asia/Tokyo")())
  })
}
  