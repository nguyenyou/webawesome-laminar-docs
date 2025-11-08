package examples.webawesome.formatBytes.h89a77beecab7
  
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
      FormatBytes(_.value := 12)()
      FormatBytes(_.value := 1200)()
      FormatBytes(_.value := 1200000)()
      FormatBytes(_.value := 1200000000)()
  })
}
  