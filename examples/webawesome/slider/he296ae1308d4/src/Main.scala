package examples.webawesome.slider.he296ae1308d4
  
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
            _.label       := "Quality",
            _.name        := "quality",
            _.withTooltip := true,
            _.hint        := "Controls the volume of the current song.",
            _.min         := 0,
            _.max         := 100,
            _.value       := "50"
          )(),
        )
      )
  })
}
  