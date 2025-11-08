package examples.webawesome.switch.h7b47a8b80233
  
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
          Switch(_.size.small)("Switch"),
          Switch(_.size.medium)("Switch"),
          Switch(_.size.large)("Switch"),
        )
      )
  })
}
  