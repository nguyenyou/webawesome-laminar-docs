package examples.webawesome.animated-image.h5663b57de523
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      AnimatedImage(
        _.src := "https://shoelace.style/assets/images/walk.gif",
        _.alt := "Animation of untied shoes walking on pavement"
      )()
  })
}
  