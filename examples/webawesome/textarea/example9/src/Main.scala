package examples.webawesome.textarea.example9
  
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
      Textarea(_.placeholder := "Small", _.size.small)()
      Textarea(_.placeholder := "Medium", _.size.medium)()
      Textarea(_.placeholder := "Large", _.size.large)()
  })
}
  