package examples.webawesome.slider.h153e8f22d8ea
  
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
            _.label := "Small",
            _.size.small,
            _.value := "50"
          )(),
          Slider(
            _.label := "Medium",
            _.size.medium,
            _.value := "50"
          )(),
          Slider(
            _.label := "Large",
            _.size.large,
            _.value := "50"
          )(),
        )
      )
  })
}
  