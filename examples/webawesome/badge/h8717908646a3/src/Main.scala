package examples.webawesome.badge.h8717908646a3
  
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
          Badge(_.variant.brand)("Brand"),
          Badge(_.variant.success)("Success"),
          Badge(_.variant.neutral)("Neutral"),
          Badge(_.variant.warning)("Warning"),
          Badge(_.variant.danger)("Danger"),
        )
      )
  })
}
  