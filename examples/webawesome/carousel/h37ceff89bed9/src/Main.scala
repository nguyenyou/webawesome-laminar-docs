package examples.webawesome.carousel.h37ceff89bed9
  
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
        _.navigation := true,
        _.pagination := true,
        _.style      := "--aspect-ratio: 3/2;"
      )(
        CarouselItem()(
          img(
            alt := "The sun shines on the mountains and trees (by Adam Kool on Unsplash)",
            src := "https://images.unsplash.com/photo-1426604966848-d7adac402bff?q=10"
          )
        ),
        CarouselItem()(
          img(
            alt := "A river winding through an evergreen forest (by Luca Bravo on Unsplash)",
            src := "https://images.unsplash.com/photo-1473448912268-2022ce9509d8?q=10"
          )
        ),
        CarouselItem()(
          img(
            alt := "The sun is setting over a lavender field (by Leonard Cotte on Unsplash)",
            src := "https://images.unsplash.com/photo-1499002238440-d264edd596ec?q=10"
          )
        ),
        CarouselItem()(
          img(
            alt := "A field of grass with the sun setting in the background (by Sapan Patel on Unsplash)",
            src := "https://images.unsplash.com/photo-1475113548554-5a36f1f523d6?q=10"
          )
        ),
        CarouselItem()(
          img(
            alt := "A scenic view of a mountain with clouds rolling in (by V2osk on Unsplash)",
            src := "https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?q=10"
          )
        )
      )
  })
}
  