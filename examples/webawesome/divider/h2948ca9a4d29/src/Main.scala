package examples.webawesome.divider.h2948ca9a4d29
  
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
            display.flex,
            alignItems.center,
            "First",
            Divider(
              _.orientation.vertical // [!code highlight]
            )(),
            "Middle",
            Divider(
              _.orientation.vertical // [!code highlight]
            )(),
            "Last"
          ),
        )
      )
  })
}
  