package examples.webawesome.popover.h3b3ea90fdb23
  
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
              _.forId := "popover__opening"
            )(
              div(
                tw.flex.flexCol.gap4,
                "Click the button below to close the popover",
                Button(
                  _.variant.brand,
                  _.close.popover
                )("Dismiss")
              )
            ),
            Button(
              _.id := "popover__opening"
            )("Show popover")
          ),
        )
      )
  })
}
  