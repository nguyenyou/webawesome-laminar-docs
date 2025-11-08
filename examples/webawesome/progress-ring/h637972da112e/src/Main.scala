package examples.webawesome.`progress-ring`.h637972da112e
  
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
            _.style := "--size: 200px;"
          )(),
        )
      )
  })
}
  