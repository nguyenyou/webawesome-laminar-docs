package examples.webawesome.callout.example2
  
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
        _.slots.icon(Icon(_.name := "circle-info")())
      )(
        strong("This is super informative"),
        br(),
        "You can tell by how pretty the callout is."
      )
      Callout(
        _.variant.success,
        _.slots.icon(Icon(_.name := "circle-check")())
      )(
        strong("Your changes have been saved"),
        br(),
        "You can safely exit the app now."
      )
      Callout(
        _.variant.neutral,
        _.slots.icon(Icon(_.name := "gear")())
      )(
        strong("Your settings have been updated"),
        br(),
        "Settings will take effect on next login."
      )
      Callout(
        _.variant.warning,
        _.slots.icon(Icon(_.name := "triangle-exclamation")())
      )(
        strong("Your session has ended"),
        br(),
        "Please login again to continue."
      )
      Callout(
        _.variant.danger,
        _.slots.icon(Icon(_.name := "circle-exclamation")())
      )(
        strong("Your account has been deleted"),
        br(),
        "We're very sorry to see you go!"
      )
  })
}
  