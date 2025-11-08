package examples.webawesome.tag.h1bf88316df2a
  
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
            tw.spaceY4,
            p(
              tw.flex.flexWrap.gap2,
              Tag(_.variant.brand, _.appearance.accent)("Accent"),
              Tag(_.variant.brand, _.appearance.filledOutlined)("Filled + Outlined"),
              Tag(_.variant.brand, _.appearance.filled)("Filled"),
              Tag(_.variant.brand, _.appearance.outlined)("Outlined")
            ),
            p(
              tw.flex.flexWrap.gap2,
              Tag(_.variant.success, _.appearance.accent)("Accent"),
              Tag(_.variant.success, _.appearance.filledOutlined)("Filled + Outlined"),
              Tag(_.variant.success, _.appearance.filled)("Filled"),
              Tag(_.variant.success, _.appearance.outlined)("Outlined")
            ),
            p(
              tw.flex.flexWrap.gap2,
              Tag(_.variant.neutral, _.appearance.accent)("Accent"),
              Tag(_.variant.neutral, _.appearance.filledOutlined)("Filled + Outlined"),
              Tag(_.variant.neutral, _.appearance.filled)("Filled"),
              Tag(_.variant.neutral, _.appearance.outlined)("Outlined")
            ),
            p(
              tw.flex.flexWrap.gap2,
              Tag(_.variant.warning, _.appearance.accent)("Accent"),
              Tag(_.variant.warning, _.appearance.filledOutlined)("Filled + Outlined"),
              Tag(_.variant.warning, _.appearance.filled)("Filled"),
              Tag(_.variant.warning, _.appearance.outlined)("Outlined")
            ),
            p(
              tw.flex.flexWrap.gap2,
              Tag(_.variant.danger, _.appearance.accent)("Accent"),
              Tag(_.variant.danger, _.appearance.filledOutlined)("Filled + Outlined"),
              Tag(_.variant.danger, _.appearance.filled)("Filled"),
              Tag(_.variant.danger, _.appearance.outlined)("Outlined")
            )
          ),
        )
      )
  })
}
  