package examples.webawesome.`color-picker`.h8bab5c2386ab
  
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
            _.size.small,
            _.label := "Select a color"
          )(),
          ColorPicker(
            _.size.medium,
            _.label := "Select a color"
          )(),
          ColorPicker(
            _.size.large,
            _.label := "Select a color"
          )(),
        )
      )
  })
}
  