package examples.webawesome.checkbox.hfbda1c4b4557
  
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
          Checkbox(_.disabled := true)("Disabled"),
        )
      )
  })
}
  