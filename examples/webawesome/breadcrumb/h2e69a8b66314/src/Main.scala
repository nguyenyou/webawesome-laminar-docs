package examples.webawesome.breadcrumb.h2e69a8b66314
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

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
  