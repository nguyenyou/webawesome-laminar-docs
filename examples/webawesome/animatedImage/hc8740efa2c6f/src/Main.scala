package examples.webawesome.animatedImage.hc8740efa2c6f
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
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
      )
  })
}
  