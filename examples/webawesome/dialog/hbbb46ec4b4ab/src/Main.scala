package examples.webawesome.dialog.hbbb46ec4b4ab
  
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
          val openEvent = EventBus[Boolean](),
          div(
            Dialog(
              _.open <-- openEvent,
              _.label := "Dialog",
              _.slots.footer(
                Button(
                  _.variant.brand,
                  _.close.dialog
                )("Close")
              )
            )("Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Button()(
              onClick.mapTo(true) --> openEvent,
              "Open Dialog"
            )
          ),
        )
      )
  })
}
  