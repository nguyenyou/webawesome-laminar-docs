package examples.webawesome.tag.hc2d557af9a4f
  
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
            tw.flex.flexWrap.gap2,
            Tag(_.size.small, _.pill := true)("Small"),
            Tag(_.size.medium, _.pill := true)("Medium"),
            Tag(_.size.large, _.pill := true)("Large")
          ),
        )
      )
  })
}
  