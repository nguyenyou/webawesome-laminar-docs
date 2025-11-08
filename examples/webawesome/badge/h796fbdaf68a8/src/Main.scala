package examples.webawesome.badge.h796fbdaf68a8
  
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
          Badge(_.appearance.accent, _.variant.neutral)("Accent"),
          Badge(_.appearance.filledOutlined, _.variant.neutral)("Filled + Outlined"),
          Badge(_.appearance.filled, _.variant.neutral)("Filled"),
          Badge(_.appearance.outlined, _.variant.neutral)("Outlined"),
        ),
        Examples(
          Badge(_.appearance.accent, _.variant.brand)("Accent"),
          Badge(_.appearance.filledOutlined, _.variant.brand)("Filled + Outlined"),
          Badge(_.appearance.filled, _.variant.brand)("Filled"),
          Badge(_.appearance.outlined, _.variant.brand)("Outlined"),
        ),
        Examples(
          Badge(_.appearance.accent, _.variant.success)("Accent"),
          Badge(_.appearance.filledOutlined, _.variant.success)("Filled + Outlined"),
          Badge(_.appearance.filled, _.variant.success)("Filled"),
          Badge(_.appearance.outlined, _.variant.success)("Outlined"),
        ),
        Examples(
          Badge(_.appearance.accent, _.variant.warning)("Accent"),
          Badge(_.appearance.filledOutlined, _.variant.warning)("Filled + Outlined"),
          Badge(_.appearance.filled, _.variant.warning)("Filled"),
          Badge(_.appearance.outlined, _.variant.warning)("Outlined"),
        ),
        Examples(
          Badge(_.appearance.accent, _.variant.danger)("Accent"),
          Badge(_.appearance.filledOutlined, _.variant.danger)("Filled + Outlined"),
          Badge(_.appearance.filled, _.variant.danger)("Filled"),
          Badge(_.appearance.outlined, _.variant.danger)("Outlined"),
        )
      )
  })
}
  