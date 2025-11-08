package examples.webawesome_button_a39ea805ed4a
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.variant.brand, _.loading := true)("Brand"),
        Button(_.variant.danger, _.loading := true)("Danger"),
        Button(_.variant.neutral, _.loading := true)("Neutral"),
        Button(_.variant.success, _.loading := true)("Success"),
        Button(_.variant.warning, _.loading := true)("Warning"),
      )
  })
}
  