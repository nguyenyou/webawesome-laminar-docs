package examples.webawesome.`color-picker`.h1feb89e3384d
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ColorPicker(_.label := "Select a color")()
  })
}
  