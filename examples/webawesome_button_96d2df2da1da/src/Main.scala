package examples.webawesome_button_96d2df2da1da
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.size.small, _.withCaret := true)("Small"),
        Button(_.size.medium, _.withCaret := true)("Medium"),
        Button(_.size.large, _.withCaret := true)("Large"),
      )
  })
}
  