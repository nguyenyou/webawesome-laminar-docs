package examples.webawesome.formatNumber.h6969198d4b82
  
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
      div("Default: ", FormatNumber(_.value := 3.14159)())
      div("Min 2 digits: ", FormatNumber(_.value := 3.14159, _.minimumFractionDigits := 2.0)())
      div("Max 2 digits: ", FormatNumber(_.value := 3.14159, _.maximumFractionDigits := 2.0)())
      div(
        "Min 0, Max 0: ",
        FormatNumber(_.value := 3.14159, _.minimumFractionDigits := 0.0, _.maximumFractionDigits := 0.0)()
      )
  })
}
  