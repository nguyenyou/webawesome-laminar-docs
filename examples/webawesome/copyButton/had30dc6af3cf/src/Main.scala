package examples.webawesome.copyButton.had30dc6af3cf
  
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
        _.value := "Copied from a custom button",
        _.slots.copyIcon(Icon(_.name := "clipboard", _.variant := "regular")()),
        _.slots.successIcon(Icon(_.name := "thumbs-up", _.variant := "solid")()),
        _.slots.errorIcon(Icon(_.name := "xmark", _.variant := "solid")())
      )()
  })
}
  