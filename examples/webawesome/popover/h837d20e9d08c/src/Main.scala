package examples.webawesome.popover.h837d20e9d08c
  
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
          div(
            Popover(
              _.forId := "popover__autofocus"
            )(
              div(
                tw.flex.flexCol.gap4,
                Textarea(
                  _.autofocus   := true,
                  _.placeholder := "What's on your mind?",
                  _.size.small,
                  _.resize := "none",
                  _.rows   := 3
                )(),
                Button(
                  _.variant.brand,
                  _.size.small,
                  _.close.popover
                )("Submit")
              )
            ),
            Button(
              _.id := "popover__autofocus",
              _.slots.start(
                Icon(
                  _.name := "comment"
                )()
              )
            )(
              "Feedback"
            )
          ),
        )
      )
  })
}
  