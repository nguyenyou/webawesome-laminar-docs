package examples.webawesome.copy-button.hf5a144a26b25
  
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
            _.value            := "Web Awesome rocks!",
            _.feedbackDuration := 250
          )(),
        )
      )
  })
}
  