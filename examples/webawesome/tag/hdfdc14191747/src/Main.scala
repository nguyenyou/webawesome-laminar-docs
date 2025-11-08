package examples.webawesome.tag.hdfdc14191747
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        cls("space-y-4"),
        p(
          cls("flex flex-wrap gap-2"),
          Tag(_.variant.brand, _.appearance.accent)("Accent"),
          Tag(_.variant.brand, _.appearance.filledOutlined)("Filled + Outlined"),
          Tag(_.variant.brand, _.appearance.filled)("Filled"),
          Tag(_.variant.brand, _.appearance.outlined)("Outlined")
        ),
        p(
          cls("flex flex-wrap gap-2"),
          Tag(_.variant.success, _.appearance.accent)("Accent"),
          Tag(_.variant.success, _.appearance.filledOutlined)("Filled + Outlined"),
          Tag(_.variant.success, _.appearance.filled)("Filled"),
          Tag(_.variant.success, _.appearance.outlined)("Outlined")
        ),
        p(
          cls("flex flex-wrap gap-2"),
          Tag(_.variant.neutral, _.appearance.accent)("Accent"),
          Tag(_.variant.neutral, _.appearance.filledOutlined)("Filled + Outlined"),
          Tag(_.variant.neutral, _.appearance.filled)("Filled"),
          Tag(_.variant.neutral, _.appearance.outlined)("Outlined")
        ),
        p(
          cls("flex flex-wrap gap-2"),
          Tag(_.variant.warning, _.appearance.accent)("Accent"),
          Tag(_.variant.warning, _.appearance.filledOutlined)("Filled + Outlined"),
          Tag(_.variant.warning, _.appearance.filled)("Filled"),
          Tag(_.variant.warning, _.appearance.outlined)("Outlined")
        ),
        p(
          cls("flex flex-wrap gap-2"),
          Tag(_.variant.danger, _.appearance.accent)("Accent"),
          Tag(_.variant.danger, _.appearance.filledOutlined)("Filled + Outlined"),
          Tag(_.variant.danger, _.appearance.filled)("Filled"),
          Tag(_.variant.danger, _.appearance.outlined)("Outlined")
        )
      )
  })
}
  