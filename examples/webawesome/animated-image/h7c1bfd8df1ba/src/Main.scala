package examples.webawesome.animated-image.h7c1bfd8df1ba
  
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
          AnimatedImage(
            _.src   := "https://shoelace.style/assets/images/walk.gif",
            _.alt   := "Animation of untied shoes walking on pavement",
            _.style := "width: 150px; height: 200px;"
          )(),
        )
      )
  })
}
  