package examples.webawesome_button_3bbad85eb7a5
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.variant.brand, _.disabled := true)("Brand"),
        Button(_.variant.danger, _.disabled := true)("Danger"),
        Button(_.variant.neutral, _.disabled := true)("Neutral"),
        Button(_.variant.success, _.disabled := true)("Success"),
        Button(_.variant.warning, _.disabled := true)("Warning"),
      )
  })
}
  