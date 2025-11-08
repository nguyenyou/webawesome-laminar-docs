package examples.webawesome.`color-picker`.h2ec61e7f237f
  
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
            _.disabled := true,
            _.label    := "Select a color"
          )(),
        )
      )
  })
}
  