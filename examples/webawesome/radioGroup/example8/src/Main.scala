package examples.webawesome.radioGroup.example8
  
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
        onSubmit.preventDefault --> Observer[dom.Event] { _ =>
          dom.window.alert("All fields are valid!")
        },
        RadioGroup(
          _.label    := "Select an option",
          _.name     := "a",
          _.required := true
        )(
          Radio(_.value := "1")("Option 1"),
          Radio(_.value := "2")("Option 2"),
          Radio(_.value := "3")("Option 3")
        ),
        br(),
        Button(
          _.typ.submit,
          _.variant.brand
        )("Submit")
      )
  })
}
  