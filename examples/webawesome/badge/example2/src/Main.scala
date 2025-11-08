package examples.webawesome.badge.example2
  
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
      Badge(_.variant.brand)("Brand")
      Badge(_.variant.success)("Success")
      Badge(_.variant.neutral)("Neutral")
      Badge(_.variant.warning)("Warning")
      Badge(_.variant.danger)("Danger")
  })
}
  