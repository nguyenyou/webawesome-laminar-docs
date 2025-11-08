package examples.webawesome.tooltip.h3227c92cdaeb
  
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
          val openVar = Var(false),
          div(
            Button()(
              onClick --> Observer { _ =>
                openVar.update(!_)
              },
              marginRight.rem(4),
              "Toggle Manually"
            ),
            Tooltip(
              _.open <-- openVar,
              _.forId   := "manual-trigger-tooltip",
              _.trigger := "manual"
            )("This is an avatar!"),
            Avatar(_.id := "manual-trigger-tooltip")()
          ),
        )
      )
  })
}
  