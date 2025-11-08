package examples.webawesome.breadcrumb.h94d46b01111b
  
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
      Breadcrumb(
        _.slots.separator(Icon(_.name := "angles-right", _.variant := "solid")()) // [!code highlight]
      )(
        BreadcrumbItem()("First"),
        BreadcrumbItem()("Second"),
        BreadcrumbItem()("Third")
      )
      Breadcrumb(
        _.slots.separator(Icon(_.name := "arrow-right", _.variant := "solid")()) // [!code highlight]
      )(
        BreadcrumbItem()("First"),
        BreadcrumbItem()("Second"),
        BreadcrumbItem()("Third")
      )
      Breadcrumb(
        _.slots.separator(span("/")) // [!code highlight]
      )(
        BreadcrumbItem()("First"),
        BreadcrumbItem()("Second"),
        BreadcrumbItem()("Third")
      )
  })
}
  