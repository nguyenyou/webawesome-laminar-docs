package examples.webawesome.`zoomable-frame`.h0afe130ebc40
  
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
          ZoomableFrame(
            _.src        := "https://webawesome.com/",
            _.zoom       := 0.5,
            _.zoomLevels := "50% 0.75 100%"
          )(),
        )
      )
  })
}
  