package examples.webawesome.card.habd35939cfcb
  
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
            _.slots.header(
              div(
                tw.flex.justifyBetween.itemsCenter,
                "Header Title",
                Button(
                  _.appearance.plain,
                  _.slots.start(Icon(_.name := "gear", _.variant := "solid", _.label := "Settings")())
                )()
              )
            )
          )(
            maxWidth.px(300),
            "This card has a header. You can put all sorts of things in it!"
          ),
        )
      )
  })
}
  