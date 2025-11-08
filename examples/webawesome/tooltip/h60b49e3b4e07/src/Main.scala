package examples.webawesome.tooltip.h60b49e3b4e07
  
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
              _.forId := "no-arrow",
              _.style := "--wa-tooltip-arrow-size: 0;"
            )("This is a tooltip with no arrow"),
            Button(_.id := "no-arrow")("No Arrow")
          ),
        )
      )
  })
}
  