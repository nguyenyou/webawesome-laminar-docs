package examples.webawesome.badge.hb08a53b30de4
  
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
      Badge(
        _.variant.brand
      )(
        fontSize.px(12), // [!code highlight]
        "Brand"
      )
      Badge(
        _.variant.brand
      )(
        fontSize.px(14), // [!code highlight]
        "Brand"
      )
      Badge(
        _.variant.brand
      )(
        fontSize.px(16), // [!code highlight]
        "Brand"
      )
      Badge(
        _.variant.brand
      )(
        fontSize.px(18), // [!code highlight]
        "Brand"
      )
      Badge(
        _.variant.brand
      )(
        fontSize.px(20), // [!code highlight]
        "Brand"
      )
  })
}
  