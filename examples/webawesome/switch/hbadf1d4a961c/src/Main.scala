package examples.webawesome.switch.hbadf1d4a961c
  
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
          Switch(_.disabled := true)("Switch"),
        )
      )
  })
}
  