package examples.webawesome.slider.h4fc889ee51d8
  
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
            _.label           := "User Friendliness",
            _.hint            := "Did you find our product easy to use?",
            _.name            := "value",
            _.value           := "0",
            _.min             := -5,
            _.max             := 5,
            _.indicatorOffset := 0,
            _.withMarkers     := true,
            _.withTooltip     := true,
            _.slots.reference(span("Easy")),
            _.slots.reference(span("Moderate")),
            _.slots.reference(span("Difficult"))
          )(),
        )
      )
  })
}
  