package examples.webawesome.zoomable-frame.h941ea18ca655
  
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
            _.srcdoc := "<html><body><h1>Hello, World!</h1><p>This is inline content.</p></body></html>"
          )(),
        )
      )
  })
}
  