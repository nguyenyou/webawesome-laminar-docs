package examples.webawesome.carousel.example7
  
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
      Carousel(
        _.navigation    := true,
        _.pagination    := true,
        _.slidesPerPage := 2,
        _.slidesPerMove := 2
      )(
        CarouselItem(
          _.style := "background: red;"
        )("Slide 1"),
        CarouselItem(
          _.style := "background: orange;"
        )("Slide 2"),
        CarouselItem(
          _.style := "background: yellow;"
        )("Slide 3"),
        CarouselItem(
          _.style := "background: green;"
        )("Slide 4"),
        CarouselItem(
          _.style := "background: blue;"
        )("Slide 5"),
        CarouselItem(
          _.style := "background: purple;"
        )("Slide 6")
      )
  })
}
  