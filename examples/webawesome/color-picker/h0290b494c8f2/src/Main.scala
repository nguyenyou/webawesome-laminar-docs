package examples.webawesome.`color-picker`.h0290b494c8f2
  
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
          ColorPicker(
            _.value := "#4a90e2",
            _.label := "Select a color"
          )(),
        )
      )
  })
}
  