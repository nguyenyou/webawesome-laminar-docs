package examples.webawesome.progress-ring.h273bc1182e61
  
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
          ProgressRing(
            _.value := 50,
            _.style := "--track-width: 6px; --indicator-width: 12px;"
          )(),
        )
      )
  })
}
  