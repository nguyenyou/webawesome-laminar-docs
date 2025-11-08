package examples.webawesome.breadcrumb.h3eac12c0a9d2
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ExampleGroups(
        Examples(
          Breadcrumb()(
            BreadcrumbItem()("Homepage"),
            BreadcrumbItem()(
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
            ),
            BreadcrumbItem()("Our Services"),
            BreadcrumbItem()("Digital Media")
          ),
        )
      )
  })
}
  