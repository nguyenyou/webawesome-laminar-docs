package examples.webawesome.avatar.h2390669b2c93
  
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
          Avatar(
            _.shape.square, // [!code highlight]
            _.label := "Square avatar"
          )(),
          Avatar(
            _.shape.rounded, // [!code highlight]
            _.label := "Rounded avatar"
          )(),
          Avatar(
            _.shape.circle, // [!code highlight]
            _.label := "Circle avatar"
          )(),
        )
      )
  })
}
  