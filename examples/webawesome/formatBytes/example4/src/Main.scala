package examples.webawesome.formatBytes.example4
  
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
      FormatBytes(_.value := 1200000, _.display.long)()
      FormatBytes(_.value := 1200000, _.display.short)()
      FormatBytes(_.value := 1200000, _.display.narrow)()
  })
}
  