package examples.webawesome.switch.h3b026e10dacc
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      val checked = Var(true)
      Switch(
        _.checked <-- checked,
        _.onInput.mapToChecked --> checked
      )("Switch")
  })
}
  