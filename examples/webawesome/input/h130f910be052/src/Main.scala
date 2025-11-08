package examples.webawesome.input.h130f910be052
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      val valueVar = Var("Enter something...")
      Input(
        _.value <-- valueVar,
        _.onInput.mapToValue --> valueVar
      )()
  })
}
  