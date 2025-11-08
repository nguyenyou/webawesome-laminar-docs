package examples.webawesome.zoomable-frame.ha8a5f6d9414b
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ZoomableFrame(
        _.src  := "https://webawesome.com/",
        _.zoom := 0.5
      )()
  })
}
  