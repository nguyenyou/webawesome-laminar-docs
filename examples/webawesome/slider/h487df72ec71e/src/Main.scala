package examples.webawesome.slider.h487df72ec71e
  
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
            _.label       := "Between zero and one",
            _.hint        := "Controls the volume of the current song.",
            _.withTooltip := true,
            _.min         := 0,
            _.max         := 1,
            _.step        := 0.1,
            _.value       := "0.5"
          )(),
        )
      )
  })
}
  