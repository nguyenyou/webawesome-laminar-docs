package examples.webawesome.`copy-button`.hae419e8c7045
  
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
          CopyButton(_.from := "i-do-not-exist")(),
        )
      )
  })
}
  