package examples.webawesome.progress-bar.h1240628e3e9a
  
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
          ProgressBar(_.indeterminate := true)(),
        )
      )
  })
}
  