package examples.webawesome.formatDate.hc91fa3dd41df
  
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
      div("12-hour: ", FormatDate(_.hour.numeric, _.minute.numeric, _.hourFormat := "12")())
      div("24-hour: ", FormatDate(_.hour.numeric, _.minute.numeric, _.hourFormat := "24")())
  })
}
  