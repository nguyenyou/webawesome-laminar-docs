package examples.webawesome.card.h747d7f24e65d
  
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
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1547191783-94d5f8f6d8b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=400&q=80",
                alt := "A kitten walks towards camera on top of pallet."
              )
            )
          )(
            maxWidth.px(300),
            "This is a kitten, but not just any kitten. This kitten likes walking along pallets."
          ),
          Card(
            _.slots.media(
              videoTag(
                sourceTag(src := "https://uploads.webawesome.com/dog-with-glasses.mp4"),
                p("Your browser doesn't support HTML video")
              )
            )
          )(
            maxWidth.px(300),
            "This is a kitten, but not just any kitten. This kitten likes walking along pallets."
          ),
        )
      )
  })
}
  