package examples.webawesome.`color-picker`.h4aa62b3914c5
  
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
            _.label := "Select a color",
            _.hint  := "Choose a color with appropriate contrast!"
          )(),
        )
      )
  })
}
  