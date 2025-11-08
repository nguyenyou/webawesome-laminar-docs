package examples.webawesome.carousel.ha8d45912e8f7
  
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
          ),
        )
      )
  })
}
  