package examples.webawesome.checkbox.hea44dd7e4992
  
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
      form(
        cls("flex flex-col items-start gap-4"),
        Checkbox(
          _.name     := "agree",
          _.required := true
        )("Check me"),
        Button(
          _.`type`.submit,
          _.variant.brand
        )("Submit")
      )
  })
}
  