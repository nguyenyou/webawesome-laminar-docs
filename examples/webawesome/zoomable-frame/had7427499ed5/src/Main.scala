package examples.webawesome.zoomable-frame.had7427499ed5
  
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
          ZoomableFrame(_.src := "https://example.com/")(
            styleAttr := "aspect-ratio: 4/3;"
          ),
        )
      )
  })
}
  