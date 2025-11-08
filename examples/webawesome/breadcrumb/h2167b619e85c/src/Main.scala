package examples.webawesome.breadcrumb.h2167b619e85c
  
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
            BreadcrumbItem(
              _.slots.start(Icon(_.name := "house")())
            )("Home"),
            BreadcrumbItem()("Articles"),
            BreadcrumbItem(
              _.slots.end(Icon(_.name := "tree-palm")())
            )("Traveling")
          ),
        )
      )
  })
}
  