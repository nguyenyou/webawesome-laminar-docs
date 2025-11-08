package examples.webawesome.button.h45c97ae8cb0e
  
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
          Button(_.size.small)("Small"),
          Button(_.size.medium)("Medium"),
          Button(_.size.large)("Large"),
        )
      )
  })
}
  