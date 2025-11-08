package examples.webawesome.callout.hb55816439508
  
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
        _.size.large,
        _.slots.icon(Icon(_.name := "circle-info")())
      )(
        "This is meant to be very emphasized."
      )
      Callout(
        _.size.medium,
        _.slots.icon(Icon(_.name := "circle-info")())
      )(
        "Normal-sized callout."
      )
      Callout(
        _.size.small,
        _.slots.icon(Icon(_.name := "circle-info")())
      )(
        "Just a small tip!"
      )
  })
}
  