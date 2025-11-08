package examples.webawesome.badge.ha48b684f8fdf
  
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
          Badge(
            _.variant.brand
          )(
            fontSize.px(12), // [!code highlight]
            "Brand"
          ),
          Badge(
            _.variant.brand
          )(
            fontSize.px(14), // [!code highlight]
            "Brand"
          ),
          Badge(
            _.variant.brand
          )(
            fontSize.px(16), // [!code highlight]
            "Brand"
          ),
          Badge(
            _.variant.brand
          )(
            fontSize.px(18), // [!code highlight]
            "Brand"
          ),
          Badge(
            _.variant.brand
          )(
            fontSize.px(20), // [!code highlight]
            "Brand"
          ),
        )
      )
  })
}
  