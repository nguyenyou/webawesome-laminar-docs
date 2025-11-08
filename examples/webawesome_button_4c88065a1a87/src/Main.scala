package examples.webawesome_button_4c88065a1a87
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.appearance.accent, _.variant.neutral)(
          Icon(_.name := "house", _.label := "Home")()
        ),
        Button(_.appearance.filled, _.variant.neutral)(
          Icon(_.name := "house", _.label := "Home")()
        ),
        Button(_.appearance.outlined, _.variant.neutral)(
          Icon(_.name := "house", _.label := "Home")()
        ),
        Button(_.appearance.plain, _.variant.neutral)(
          Icon(_.name := "house", _.label := "Home")()
        ),
      )
  })
}
  