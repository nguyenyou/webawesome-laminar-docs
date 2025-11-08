package examples.webawesome.textarea.hb144caadc18b
  
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
          Textarea(_.label := "Feedback", _.hint := "Please tell us what you think.")(),
        )
      )
  })
}
  