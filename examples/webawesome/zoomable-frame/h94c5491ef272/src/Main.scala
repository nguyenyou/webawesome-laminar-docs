package examples.webawesome.`zoomable-frame`.h94c5491ef272
  
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
            _.src             := "https://webawesome.com/",
            _.withoutControls := true,
            _.zoom            := 0.5
          )(),
        )
      )
  })
}
  