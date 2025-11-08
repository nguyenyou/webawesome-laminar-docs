package examples.webawesome.breadcrumb.hab059316e9dc
  
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
          ),
        )
      )
  })
}
  