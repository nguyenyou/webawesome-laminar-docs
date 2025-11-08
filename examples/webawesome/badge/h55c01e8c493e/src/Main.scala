package examples.webawesome.badge.h55c01e8c493e
  
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
      Button()(
        "Requests",
        Badge(_.pill := true)("30")
      )
      Button()(
        "Warnings",
        Badge(_.variant.warning, _.pill := true)("8")
      )
      Button()(
        "Errors",
        Badge(_.variant.danger, _.pill := true)("6")
      )
  })
}
  