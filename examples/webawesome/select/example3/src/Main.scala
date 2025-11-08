package examples.webawesome.select.example3
  
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
        _.label := "Experience",
        _.hint  := "Please tell us your skill level."
      )(
        UOption(_.value := "1")("Novice"),
        UOption(_.value := "2")("Intermediate"),
        UOption(_.value := "3")("Advanced")
      )
  })
}
  