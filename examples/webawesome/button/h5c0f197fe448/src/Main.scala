package examples.webawesome.button.h5c0f197fe448
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.size.small, _.pill := true)("Small"),
        Button(_.size.medium, _.pill := true)("Medium"),
        Button(_.size.large, _.pill := true)("Large"),
      )
  })
}
  