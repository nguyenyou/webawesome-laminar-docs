package examples.webawesome.formatDate.example2
  
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
      div("Human-readable date: ", FormatDate(_.month.long, _.day.numeric, _.year.numeric)())
      div("Time: ", FormatDate(_.hour.numeric, _.minute.numeric)())
      div("Weekday: ", FormatDate(_.weekday.long)())
      div("Month: ", FormatDate(_.month.long)())
      div("Year: ", FormatDate(_.year.numeric)())
      div("No formatting options: ", FormatDate()())
  })
}
  