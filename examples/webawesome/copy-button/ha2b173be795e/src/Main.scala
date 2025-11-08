package examples.webawesome.`copy-button`.ha2b173be795e
  
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
          CopyButton(
            _.value    := "You can't copy me",
            _.disabled := true
          )(),
        )
      )
  })
}
  