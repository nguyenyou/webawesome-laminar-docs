package examples.webawesome.radioGroup.example4
  
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
      RadioGroup(
        _.label    := "Select an option",
        _.disabled := true
      )(
        Radio(_.value := "1")("Option 1"),
        Radio(_.value := "2", _.disabled := true)("Option 2"),
        Radio(_.value := "3")("Option 3")
      )
  })
}
  