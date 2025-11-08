package examples.webawesome.animated-image.h955ba96a278b
  
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
            styleTag("""
              .animated-image-custom-control-box::part(control-box) {
                top: auto;
                right: auto;
                bottom: 1rem;
                left: 1rem;
                background-color: deeppink;
                border: none;
                color: pink;
              }
            """),
            AnimatedImage(
              _.src := "https://shoelace.style/assets/images/walk.gif",
              _.alt := "Animation of untied shoes walking on pavement"
            )(
              cls := "animated-image-custom-control-box"
            )
          ),
        )
      )
  })
}
  