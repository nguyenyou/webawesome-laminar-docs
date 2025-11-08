package examples.webawesome.badge.h4d69d6671174
  
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
          Badge(_.variant.brand, _.pill := true)("Brand"),
          Badge(_.variant.success, _.pill := true)("Success"),
          Badge(_.variant.neutral, _.pill := true)("Neutral"),
          Badge(_.variant.warning, _.pill := true)("Warning"),
          Badge(_.variant.danger, _.pill := true)("Danger"),
        )
      )
  })
}
  