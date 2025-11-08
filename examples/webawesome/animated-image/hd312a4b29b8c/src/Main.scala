package examples.webawesome.animated-image.hd312a4b29b8c
  
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
            _.src := "https://shoelace.style/assets/images/tie.webp",
            _.alt := "Animation of a shoe being tied"
          )(),
        )
      )
  })
}
  