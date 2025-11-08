package examples.webawesome.breadcrumb.h32255e1ec510
  
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
        BreadcrumbItem()("Homepage"),
        BreadcrumbItem()("Our Services"),
        BreadcrumbItem()("Digital Media"),
        BreadcrumbItem(
          _.slots.end(
            Dropdown(
              _.slots.trigger(
                Button(_.size.small, _.appearance.filled, _.pill := true)(
                  Icon(_.label := "More options", _.name := "ellipsis", _.variant := "solid")() // [!code highlight]
                )
              )
            )(
              DropdownItem(_.`type` := "checkbox", _.checked := true)("Web Design"),
              DropdownItem(_.`type` := "checkbox")("Web Development"),
              DropdownItem(_.`type` := "checkbox")("Marketing")
            )
          )
        )("Web Design")
      )
  })
}
  