package examples.webawesome.slider.h8cc90648be96
  
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
            _.label       := "Speed",
            _.name        := "speed",
            _.withMarkers := true,
            _.min         := 1,
            _.max         := 5,
            _.value       := "3",
            _.hint        := "Controls the speed of the thing you're currently doing.",
            _.slots.reference(span("Slow")),
            _.slots.reference(span("Medium")),
            _.slots.reference(span("Fast"))
          )(),
        )
      )
  })
}
  