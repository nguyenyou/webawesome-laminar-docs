package examples.webawesome.textarea.h5ea88b6c1dd7
  
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
          Textarea(_.placeholder := "Textarea", _.disabled := true)(),
        )
      )
  })
}
  