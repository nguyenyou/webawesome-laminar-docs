package examples.webawesome.input.h7e0b75708576
  
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
      Input(
        _.typ.email,
        _.placeholder := "Email"
      )()
      Input(
        _.typ.number,
        _.placeholder := "Number"
      )()
      Input(
        _.typ.date,
        _.placeholder := "Date"
      )()
  })
}
  