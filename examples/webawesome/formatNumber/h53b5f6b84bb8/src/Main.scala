package examples.webawesome.formatNumber.h53b5f6b84bb8
  
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
      div("USD: ", FormatNumber(_.`type`.currency, _.currency := "USD", _.value := 2000)())
      div("GBP: ", FormatNumber(_.`type`.currency, _.currency := "GBP", _.value := 2000)())
      div("EUR: ", FormatNumber(_.`type`.currency, _.currency := "EUR", _.value := 2000)())
      div("JPY: ", FormatNumber(_.`type`.currency, _.currency := "JPY", _.value := 2000)())
      div("CNY: ", FormatNumber(_.`type`.currency, _.currency := "CNY", _.value := 2000)())
  })
}
  