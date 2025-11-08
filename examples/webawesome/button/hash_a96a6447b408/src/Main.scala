package examples.webawesome.button.hash_a96a6447b408
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Button(
        _.onClick --> Observer[dom.MouseEvent] { event =>
          dom.window.alert(s"Clicked at clientX ${event.clientX}, clientY ${event.clientY}")
        }
      )("Button")
  })
}
  