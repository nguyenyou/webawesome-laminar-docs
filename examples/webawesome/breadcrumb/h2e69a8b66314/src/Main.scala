package examples.webawesome.breadcrumb.h2e69a8b66314
  
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
      Breadcrumb()(
        BreadcrumbItem()("Catalog"),
        BreadcrumbItem()("Clothing"),
        BreadcrumbItem()("Women's"),
        BreadcrumbItem()("Shirts & Tops")
      )
  })
}
  