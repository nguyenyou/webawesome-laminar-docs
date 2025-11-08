package examples.webawesome.badge.h4280b2383218
  
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
          Badge(_.variant.brand, _.attention.pulse, _.pill := true)("1"),
          Badge(_.variant.success, _.attention.pulse, _.pill := true)("1"),
          Badge(_.variant.neutral, _.attention.pulse, _.pill := true)("1"),
          Badge(_.variant.warning, _.attention.pulse, _.pill := true)("1"),
          Badge(_.variant.danger, _.attention.pulse, _.pill := true)("1"),
        ),
        Examples(
          Badge(_.variant.brand, _.attention.bounce, _.pill := true)("1"),
          Badge(_.variant.success, _.attention.bounce, _.pill := true)("1"),
          Badge(_.variant.neutral, _.attention.bounce, _.pill := true)("1"),
          Badge(_.variant.warning, _.attention.bounce, _.pill := true)("1"),
          Badge(_.variant.danger, _.attention.bounce, _.pill := true)("1"),
        )
      )
  })
}
  