package examples.webawesome.card.h458a6d4dbdfa
  
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
      Card(
        _.slots.footer(
          div(
            cls("flex justify-between items-center"),
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
  