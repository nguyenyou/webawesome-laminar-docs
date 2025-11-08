package examples.webawesome.formatBytes.h9269f743c91d
  
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
      FormatBytes(_.value := 12, _.unit.bit)()
      FormatBytes(_.value := 1200, _.unit.bit)()
      FormatBytes(_.value := 1200000, _.unit.bit)()
      FormatBytes(_.value := 1200000000, _.unit.bit)()
  })
}
  