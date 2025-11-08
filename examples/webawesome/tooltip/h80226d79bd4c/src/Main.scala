package examples.webawesome.tooltip.h80226d79bd4c
  
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
            Tooltip(
              _.forId   := "toggle-button",
              _.trigger := "click"
            )("Click to Toggle"),
            Button(_.id := "toggle-button")("Hover Me")
          ),
        )
      )
  })
}
  