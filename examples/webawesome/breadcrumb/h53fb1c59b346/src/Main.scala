package examples.webawesome.breadcrumb.h53fb1c59b346
  
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
        BreadcrumbItem(
          _.slots.start(Icon(_.name := "house")())
        )("Home"),
        BreadcrumbItem()("Articles"),
        BreadcrumbItem(
          _.slots.end(Icon(_.name := "tree-palm")())
        )("Traveling")
      )
  })
}
  