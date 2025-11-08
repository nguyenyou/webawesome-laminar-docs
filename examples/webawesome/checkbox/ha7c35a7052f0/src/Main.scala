package examples.webawesome.checkbox.ha7c35a7052f0
  
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
          Checkbox(_.checked := true)("Checked"),
        )
      )
  })
}
  