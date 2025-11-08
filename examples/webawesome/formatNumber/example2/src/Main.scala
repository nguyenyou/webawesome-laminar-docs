package examples.webawesome.formatNumber.example2
  
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
      FormatNumber(_.`type`.percent, _.value := 0)()
      FormatNumber(_.`type`.percent, _.value := 0.25)()
      FormatNumber(_.`type`.percent, _.value := 0.50)()
      FormatNumber(_.`type`.percent, _.value := 0.75)()
      FormatNumber(_.`type`.percent, _.value := 1)()
  })
}
  