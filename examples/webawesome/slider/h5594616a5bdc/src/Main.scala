package examples.webawesome.slider.h5594616a5bdc
  
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
          div(
            tw.flex.gap4,
            Slider(
              _.label := "Volume",
              _.name  := "volume",
              _.orientation.vertical,
              _.value := "65",
              _.style := "width: 80px"
            )(),
            Slider(
              _.label := "Bass",
              _.name  := "bass",
              _.value := "50",
              _.orientation.vertical,
              _.style := "width: 80px"
            )(),
            Slider(
              _.label := "Treble",
              _.name  := "treble",
              _.value := "40",
              _.orientation.vertical,
              _.style := "width: 80px"
            )()
          ),
        )
      )
  })
}
  