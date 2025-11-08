package examples.webawesome.select.example13
  
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
      Select(
        _.placeholder := "Small",
        _.size.small
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
      Select(
        _.placeholder := "Medium",
        _.size.medium
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
      Select(
        _.placeholder := "Large",
        _.size.large
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
  })
}
  