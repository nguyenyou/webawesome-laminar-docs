package examples.webawesome.textarea.h63ca18eb4587
  
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
          Textarea(_.placeholder := "Small", _.size.small)(),
          Textarea(_.placeholder := "Medium", _.size.medium)(),
          Textarea(_.placeholder := "Large", _.size.large)(),
        )
      )
  })
}
  