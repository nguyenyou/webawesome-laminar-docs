package examples.webawesome.breadcrumb.h04512aee00f7
  
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
          Breadcrumb(
            _.slots.separator(Icon(_.name := "angles-right", _.variant := "solid")()) // [!code highlight]
          )(
            BreadcrumbItem()("First"),
            BreadcrumbItem()("Second"),
            BreadcrumbItem()("Third")
          ),
          Breadcrumb(
            _.slots.separator(Icon(_.name := "arrow-right", _.variant := "solid")()) // [!code highlight]
          )(
            BreadcrumbItem()("First"),
            BreadcrumbItem()("Second"),
            BreadcrumbItem()("Third")
          ),
          Breadcrumb(
            _.slots.separator(span("/")) // [!code highlight]
          )(
            BreadcrumbItem()("First"),
            BreadcrumbItem()("Second"),
            BreadcrumbItem()("Third")
          ),
        )
      )
  })
}
  