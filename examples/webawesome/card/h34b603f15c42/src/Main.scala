package examples.webawesome.card.h34b603f15c42
  
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
          Card(
            _.slots.footer(
              div(
                tw.flex.justifyBetween.itemsCenter,
                Rating()(),
                Button(_.variant.brand)("Preview")
              )
            )
          )(
            maxWidth.px(300),
            "This card has a footer. You can put all sorts of things in it!"
          ),
        )
      )
  })
}
  