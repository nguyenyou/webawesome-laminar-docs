package examples.webawesome.tag.h81884c94f40d
  
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
            div(
              tw.flex.flexWrap.gap2,
              Tag(_.size.small, _.withRemove := true)("Small"),
              Tag(_.size.medium, _.withRemove := true)("Medium"),
              Tag(_.size.large, _.withRemove := true)("Large")
            )
          ),
        )
      )
  })
}
  