package examples.webawesome.checkbox.h6700859fdcb1
  
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
          Checkbox(_.hint := "What should the user know about the checkbox?")("Label"),
        )
      )
  })
}
  