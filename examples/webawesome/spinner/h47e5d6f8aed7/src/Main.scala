package examples.webawesome.spinner.h47e5d6f8aed7
  
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
      Spinner()()
      Spinner(
        _.style := "font-size: 2rem;"
      )()
      Spinner(
        _.style := "font-size: 3rem;"
      )()
  })
}
  