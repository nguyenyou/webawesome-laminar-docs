package examples.webawesome.switch.hacc5129c9fe0
  
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
          Switch(_.hint := "What should the user know about the switch?")("Switch"),
        )
      )
  })
}
  