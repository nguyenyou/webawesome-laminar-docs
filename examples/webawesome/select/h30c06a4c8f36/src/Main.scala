package examples.webawesome.select.h30c06a4c8f36
  
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
        _.size.small,
        _.withClear := true,
        _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
        _.slots.end(Icon(_.name := "flag-checkered")())
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
      Select(
        _.placeholder := "Medium",
        _.size.medium,
        _.withClear := true,
        _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
        _.slots.end(Icon(_.name := "flag-checkered")())
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
      Select(
        _.placeholder := "Large",
        _.size.large,
        _.withClear := true,
        _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
        _.slots.end(Icon(_.name := "flag-checkered")())
      )(
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3")
      )
  })
}
  