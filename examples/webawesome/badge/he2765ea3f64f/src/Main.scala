package examples.webawesome.badge.he2765ea3f64f
  
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
      Badge(_.variant.brand, _.attention.pulse, _.pill := true)("1")
      Badge(_.variant.success, _.attention.pulse, _.pill := true)("1")
      Badge(_.variant.neutral, _.attention.pulse, _.pill := true)("1")
      Badge(_.variant.warning, _.attention.pulse, _.pill := true)("1")
      Badge(_.variant.danger, _.attention.pulse, _.pill := true)("1")
      
      Badge(_.variant.brand, _.attention.bounce, _.pill := true)("1")
      Badge(_.variant.success, _.attention.bounce, _.pill := true)("1")
      Badge(_.variant.neutral, _.attention.bounce, _.pill := true)("1")
      Badge(_.variant.warning, _.attention.bounce, _.pill := true)("1")
      Badge(_.variant.danger, _.attention.bounce, _.pill := true)("1")
  })
}
  