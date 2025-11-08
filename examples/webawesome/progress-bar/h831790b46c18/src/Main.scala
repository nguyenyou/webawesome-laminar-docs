package examples.webawesome.`progress-bar`.h831790b46c18
  
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
          ProgressBar(
            _.value := 50,
            _.label := "Upload progress"
          )(),
        )
      )
  })
}
  