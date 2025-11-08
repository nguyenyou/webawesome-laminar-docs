package examples.webawesome.slider.hdd8fb3cc989f
  
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
          Slider(
            _.label    := "Disabled",
            _.value    := "50",
            _.disabled := true // [!code highlight]
          )(),
        )
      )
  })
}
  