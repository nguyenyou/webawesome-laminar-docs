package examples.webawesome.progress-ring.heac226d5dfda
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ProgressRing(_.value := 25)()
  })
}
  