package examples.webawesome.switch.h58a2716019e8
  
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
          Switch(_.checked := true)("Switch"),
        )
      )
  })
}
  