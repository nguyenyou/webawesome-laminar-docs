package examples.webawesome.radioGroup.h9c98b4d4b41c
  
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
        _.size.small
      )(
        Radio(_.value := "small")("Small"),
        Radio(_.value := "medium")("Medium"),
        Radio(_.value := "large")("Large")
      )
      RadioGroup(
        _.size.medium
      )(
        Radio(_.value := "small")("Small"),
        Radio(_.value := "medium")("Medium"),
        Radio(_.value := "large")("Large")
      )
      RadioGroup(
        _.size.large
      )(
        Radio(_.value := "small")("Small"),
        Radio(_.value := "medium")("Medium"),
        Radio(_.value := "large")("Large")
      )
  })
}
  