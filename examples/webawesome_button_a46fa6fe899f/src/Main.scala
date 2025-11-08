package examples.webawesome_button_a46fa6fe899f
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.appearance.accent, _.variant.neutral)("Accent"),
        Button(_.appearance.filledOutlined, _.variant.neutral)("Filled + Outlined"),
        Button(_.appearance.filled, _.variant.neutral)("Filled"),
        Button(_.appearance.outlined, _.variant.neutral)("Outlined"),
        Button(_.appearance := "plain", _.variant := "neutral")("Plain"),
        Button(_.appearance.accent, _.variant.brand)("Accent"),
        Button(_.appearance.filledOutlined, _.variant.brand)("Filled + Outlined"),
        Button(_.appearance.filled, _.variant.brand)("Filled"),
        Button(_.appearance.outlined, _.variant.brand)("Outlined"),
        Button(_.appearance.plain, _.variant.brand)("Plain"),
        Button(_.appearance.accent, _.variant.success)("Accent"),
        Button(_.appearance.filledOutlined, _.variant.success)("Filled + Outlined"),
        Button(_.appearance.filled, _.variant.success)("Filled"),
        Button(_.appearance.outlined, _.variant.success)("Outlined"),
        Button(_.appearance.plain, _.variant.success)("Plain"),
        Button(_.appearance.accent, _.variant.warning)("Accent"),
        Button(_.appearance.filledOutlined, _.variant.warning)("Filled + Outlined"),
        Button(_.appearance.filled, _.variant.warning)("Filled"),
        Button(_.appearance.outlined, _.variant.warning)("Outlined"),
        Button(_.appearance.plain, _.variant.warning)("Plain"),
        Button(_.appearance.accent, _.variant.danger)("Accent"),
        Button(_.appearance.filledOutlined, _.variant.danger)("Filled + Outlined"),
        Button(_.appearance.filled, _.variant.danger)("Filled"),
        Button(_.appearance.outlined, _.variant.danger)("Outlined"),
        Button(_.appearance.plain, _.variant.danger)("Plain"),
      )
  })
}
  