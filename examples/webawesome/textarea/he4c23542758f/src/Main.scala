package examples.webawesome.textarea.he4c23542758f
  
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
          Textarea(_.placeholder := "Type something")(),
        )
      )
  })
}
  