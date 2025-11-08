package examples.webawesome.breadcrumb.h6a6c3c7054f2
  
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
      div(
        styleTag(
          """
          .redcrumbs wa-breadcrumb-item {
            color: firebrick;
          }
          .redcrumbs wa-breadcrumb-item:last-of-type {
            color: crimson;
          }
          .redcrumbs wa-breadcrumb-item::part(separator) {
            color: pink;
          }
          .redcrumbs wa-breadcrumb-item::part(start),
          .redcrumbs wa-breadcrumb-item::part(end) {
            color: currentColor;
          }
        """
        ),
        Breadcrumb()(
          className := "redcrumbs",
          BreadcrumbItem(
            _.slots.start(Icon(_.name := "house", _.variant := "solid")())
          )("Home"),
          BreadcrumbItem()("Articles"),
          BreadcrumbItem()("Traveling")
        )
      )
  })
}
  