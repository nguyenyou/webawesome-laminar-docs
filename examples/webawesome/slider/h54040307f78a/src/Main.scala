package examples.webawesome.slider.h54040307f78a
  
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
            _.min   := 0,
            _.max   := 100
          )(),
        )
      )
  })
}
  