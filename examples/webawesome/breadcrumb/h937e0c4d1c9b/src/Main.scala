package examples.webawesome.breadcrumb.h937e0c4d1c9b
  
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
            BreadcrumbItem(_.href := "https://example.com/home")("Homepage"),
            BreadcrumbItem(_.href := "https://example.com/home/services")("Our Services"),
            BreadcrumbItem(_.href := "https://example.com/home/services/digital")("Digital Media"),
            BreadcrumbItem(_.href := "https://example.com/home/services/digital/web-design")("Web Design")
          ),
        )
      )
  })
}
  