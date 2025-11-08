package examples.webawesome.`button-group`.h4432c4e62823
  
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
          ButtonGroup(_.size.small, _.label := "Alignment")(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.size.medium, _.label := "Alignment")(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.size.large, _.label := "Alignment")(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
        )
      )
  })
}
  