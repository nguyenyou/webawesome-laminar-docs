package examples.webawesome.button-group.h002d97651be3
  
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
          ButtonGroup(_.label := "Alignment")(
            Button(_.size.small, _.pill := true)("Left"),
            Button(_.size.small, _.pill := true)("Center"),
            Button(_.size.small, _.pill := true)("Right")
          ),
          ButtonGroup(_.label := "Alignment")(
            Button(_.size.medium, _.pill := true)("Left"),
            Button(_.size.medium, _.pill := true)("Center"),
            Button(_.size.medium, _.pill := true)("Right")
          ),
          ButtonGroup(_.label := "Alignment")(
            Button(_.size.large, _.pill := true)("Left"),
            Button(_.size.large, _.pill := true)("Center"),
            Button(_.size.large, _.pill := true)("Right")
          ),
        )
      )
  })
}
  