package examples.webawesome.badge.example5
  
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
      Badge(_.variant.brand, _.pill := true)("Brand")
      Badge(_.variant.success, _.pill := true)("Success")
      Badge(_.variant.neutral, _.pill := true)("Neutral")
      Badge(_.variant.warning, _.pill := true)("Warning")
      Badge(_.variant.danger, _.pill := true)("Danger")
  })
}
  