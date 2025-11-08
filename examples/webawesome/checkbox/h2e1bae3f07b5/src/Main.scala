package examples.webawesome.checkbox.h2e1bae3f07b5
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Checkbox()("Checkbox")
  })
}
  