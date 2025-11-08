package examples.webawesome.button-group.h7ec7d2b17ced
  
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
          ButtonGroup(_.label := "Alignment", _.variant.brand)(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.label := "Alignment", _.variant.success)(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.label := "Alignment")(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.label := "Alignment", _.variant.warning)(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
          ButtonGroup(_.label := "Alignment", _.variant.danger)(
            Button()("Left"),
            Button()("Center"),
            Button()("Right")
          ),
        )
      )
  })
}
  