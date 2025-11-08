package examples.webawesome.card.h28d2a41b048e
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Card(
        _.slots.footer(
          div(
            tw.flex.justifyBetween.itemsCenter,
            Button(_.variant.brand, _.pill := true)("More Info"),
            Rating()()
          )
        ),
        _.slots.media(
          img(
            src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
            alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
          )
        )
      )(
        width.px(300),
        strong("Mittens"),
        br(),
        "This kitten is as cute as he is playful. Bring him home today!",
        br(),
        small("6 weeks old")
      )
  })
}
  