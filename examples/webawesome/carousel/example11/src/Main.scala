package examples.webawesome.carousel.example11
  
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
      type Item = (imgSrc: String, description: String, thumbnailAlt: String)
      
      val activeSlide = Var(0.0)
      
      val items: Seq[Item] = Seq(
        (
          "https://images.unsplash.com/photo-1426604966848-d7adac402bff?q=10",
          "The sun shines on the mountains and trees (by Adam Kool on Unsplash)",
          "Thumbnail by 1"
        ),
        (
          "https://images.unsplash.com/photo-1473448912268-2022ce9509d8?q=10",
          "A river winding through an evergreen forest (by Luca Bravo on Unsplash)",
          "Thumbnail by 2"
        ),
        (
          "https://images.unsplash.com/photo-1499002238440-d264edd596ec?q=10",
          "The sun is setting over a lavender field (by Leonard Cotte on Unsplash)",
          "Thumbnail by 3"
        ),
        (
          "https://images.unsplash.com/photo-1475113548554-5a36f1f523d6?q=10",
          "A field of grass with the sun setting in the background (by Sapan Patel on Unsplash)",
          "Thumbnail by 4"
        ),
        (
          "https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?q=10",
          "A scenic view of a mountain with clouds rolling in (by V2osk on Unsplash)",
          "Thumbnail by 5"
        )
      )
      div(
        styleTag("""
          .carousel-thumbnails {
            --slide-aspect-ratio: 3 / 2;
          }
      
          .thumbnails {
            display: flex;
            justify-content: center;
          }
      
          .scroller {
            display: flex;
            gap: var(--wa-space-s);
            overflow-x: auto;
            scrollbar-width: none;
            scroll-behavior: smooth;
            scroll-padding: var(--wa-space-s);
          }
      
          .scroller::-webkit-scrollbar {
            display: none;
          }
      
          .image {
            width: 64px;
            height: 64px;
            object-fit: cover;
      
            opacity: 0.3;
            will-change: opacity;
            transition: 250ms opacity;
      
            cursor: pointer;
          }
      
          .image.active {
            opacity: 1;
          }
        """),
        Carousel(
          _.navigation := true,
          _.loop       := true,
          _.onSlideChange.map(_.detail.index.toDouble) --> activeSlide
        )(
          inContext { ctx =>
            activeSlide --> Observer[Double] { slide =>
              ctx.ref.goToSlide(slide, "smooth")
            }
          },
          items.map { item =>
            CarouselItem()(
              img(
                alt := item.description,
                src := item.imgSrc
              )
            )
          }
        ),
        div(
          cls("thumbnails"),
          div(
            cls("scroller"),
            items.zipWithIndex.map { case (item, index) =>
              img(
                alt := item.thumbnailAlt,
                cls("image"),
                cls("active") <-- activeSlide.signal.map(_ == index),
                src := item.imgSrc,
                onClick.mapTo(index.toDouble) --> activeSlide
              )
            }
          )
        )
      )
  })
}
  