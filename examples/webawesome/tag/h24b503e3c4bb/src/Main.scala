package examples.webawesome.tag.h24b503e3c4bb
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        tw.flex.flexWrap.gap2,
        Tag(_.variant.brand)("Brand"),
        Tag(_.variant.success)("Success"),
        Tag(_.variant.neutral)("Neutral"),
        Tag(_.variant.warning)("Warning"),
        Tag(_.variant.danger)("Danger")
      )
  })
}
  