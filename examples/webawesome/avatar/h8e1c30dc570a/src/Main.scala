package examples.webawesome.avatar.h8e1c30dc570a
  
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
            _.initials := "WA", // [!code highlight]
            _.label    := "Avatar with initials: SL"
          )(),
        )
      )
  })
}
  