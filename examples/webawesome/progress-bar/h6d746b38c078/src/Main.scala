package examples.webawesome.`progress-bar`.h6d746b38c078
  
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
            _.style := "--track-height: 6px;"
          )(),
        )
      )
  })
}
  