package examples.webawesome.tag.h5c12776e7551
  
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
            Tag(_.size.small)("Small"),
            Tag(_.size.medium)("Medium"),
            Tag(_.size.large)("Large")
          ),
        )
      )
  })
}
  