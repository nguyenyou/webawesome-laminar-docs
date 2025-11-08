package examples.webawesome.slider.h68312b0a3a4c
  
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
            _.label       := "Size",
            _.name        := "size",
            _.withMarkers := true,
            _.min         := 0,
            _.max         := 8,
            _.value       := "4"
          )(),
        )
      )
  })
}
  