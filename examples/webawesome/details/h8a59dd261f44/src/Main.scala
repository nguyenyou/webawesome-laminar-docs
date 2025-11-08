package examples.webawesome.details.h8a59dd261f44
  
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
          Details(
            _.summary := "Toggle Me",
            _.slots.expandIcon(Icon(_.name := "square-plus", _.variant := "regular")()),
            _.slots.collapseIcon(Icon(_.name := "square-minus", _.variant := "regular")())
          )(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
          ),
        )
      )
  })
}
  