package examples.webawesome.slider.h310dd46f54a3
  
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
            _.label := "Volumn",
            _.hint  := "Controls the volume of the current song.",
            _.min   := 0,
            _.max   := 100,
            _.value := "50"
          )(),
        )
      )
  })
}
  