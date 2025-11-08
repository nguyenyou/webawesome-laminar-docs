package examples.webawesome.textarea.hae956e90164f
  
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
          Textarea(_.placeholder := "Type something", _.appearance := "filled")(),
        )
      )
  })
}
  