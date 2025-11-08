package examples.webawesome.callout.h4ec220e147c6
  
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
      Callout(
        _.variant.brand,
        _.appearance.accent,
        _.slots.icon(Icon(_.name := "square-check")())
      )(
        "This ",
        strong("accent"),
        " callout draws attention"
      )
      Callout(
        _.variant.brand,
        _.appearance.filledOutlined,
        _.slots.icon(Icon(_.name := "fill-drip")())
      )(
        "This callout is both ",
        strong("filled"),
        " and ",
        strong("outlined")
      )
      Callout(
        _.variant.brand,
        _.appearance.filled,
        _.slots.icon(Icon(_.name := "fill")())
      )(
        "This callout is only ",
        strong("filled")
      )
      Callout(
        _.variant.brand,
        _.appearance.outlined,
        _.slots.icon(Icon(_.name := "lines-leaning")())
      )(
        "Here's an ",
        strong("outlined"),
        " callout"
      )
      Callout(
        _.variant.brand,
        _.appearance.plain,
        _.slots.icon(Icon(_.name := "font")())
      )(
        "No bells and whistles on this ",
        strong("plain"),
        " callout"
      )
  })
}
  