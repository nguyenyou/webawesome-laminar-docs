package examples.webawesome.textarea.hc61ec8d85e61
  
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
          Textarea(_.resize.auto)(),
        )
      )
  })
}
  