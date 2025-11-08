package examples.webawesome.`button-group`.h049376e4ce09
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ButtonGroup(_.label := "Alignment")(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
  })
}
  