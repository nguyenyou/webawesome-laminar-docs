package examples.webawesome.tooltip.h04ff5b3c78ff
  
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
              _.forId := "rich-tooltip",
              _.style := "--wa-tooltip-arrow-size: 0;"
            )(
              div(
                "I'm not ",
                strong("just"),
                " a tooltip, I'm a ",
                em("tooltip"),
                " with HTML!"
              )
            ),
            Button(_.id := "rich-tooltip")("Hover me")
          ),
        )
      )
  })
}
  