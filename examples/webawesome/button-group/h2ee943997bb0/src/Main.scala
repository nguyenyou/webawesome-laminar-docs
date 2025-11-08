package examples.webawesome.button-group.h2ee943997bb0
  
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
            Button(_.variant.neutral)("Right")
          ),
        )
      )
  })
}
  