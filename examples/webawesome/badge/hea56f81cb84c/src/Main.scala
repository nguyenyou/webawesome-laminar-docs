package examples.webawesome.badge.hea56f81cb84c
  
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
      Badge(_.appearance.accent, _.variant.neutral)("Accent")
      Badge(_.appearance.filledOutlined, _.variant.neutral)("Filled + Outlined")
      Badge(_.appearance.filled, _.variant.neutral)("Filled")
      Badge(_.appearance.outlined, _.variant.neutral)("Outlined")
      
      Badge(_.appearance.accent, _.variant.brand)("Accent")
      Badge(_.appearance.filledOutlined, _.variant.brand)("Filled + Outlined")
      Badge(_.appearance.filled, _.variant.brand)("Filled")
      Badge(_.appearance.outlined, _.variant.brand)("Outlined")
      
      Badge(_.appearance.accent, _.variant.success)("Accent")
      Badge(_.appearance.filledOutlined, _.variant.success)("Filled + Outlined")
      Badge(_.appearance.filled, _.variant.success)("Filled")
      Badge(_.appearance.outlined, _.variant.success)("Outlined")
      
      Badge(_.appearance.accent, _.variant.warning)("Accent")
      Badge(_.appearance.filledOutlined, _.variant.warning)("Filled + Outlined")
      Badge(_.appearance.filled, _.variant.warning)("Filled")
      Badge(_.appearance.outlined, _.variant.warning)("Outlined")
      
      Badge(_.appearance.accent, _.variant.danger)("Accent")
      Badge(_.appearance.filledOutlined, _.variant.danger)("Filled + Outlined")
      Badge(_.appearance.filled, _.variant.danger)("Filled")
      Badge(_.appearance.outlined, _.variant.danger)("Outlined")
  })
}
  