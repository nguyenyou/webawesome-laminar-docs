package examples.webawesome.select.example12
  
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
      Select()(
        small("Section 1"),
        UOption(_.value := "option-1")("Option 1"),
        UOption(_.value := "option-2")("Option 2"),
        UOption(_.value := "option-3")("Option 3"),
        Divider()(),
        small("Section 2"),
        UOption(_.value := "option-4")("Option 4"),
        UOption(_.value := "option-5")("Option 5"),
        UOption(_.value := "option-6")("Option 6")
      )
  })
}
  