package examples.webawesome.copyButton.h82cbb1595b10
  
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
      CopyButton(
        _.value        := "Custom labels are easy",
        _.copyLabel    := "Click to copy",
        _.successLabel := "You did it!",
        _.errorLabel   := "Whoops, your browser doesn't support this!"
      )()
  })
}
  