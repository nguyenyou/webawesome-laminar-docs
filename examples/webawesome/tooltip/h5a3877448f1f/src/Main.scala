package examples.webawesome.tooltip.h5a3877448f1f
  
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
              _.forId := "wrapping-tooltip",
              _.style := "--max-width: 80px;"
            )(
              "This tooltip will wrap after only 80 pixels."
            ),
            Button(_.id := "wrapping-tooltip")("Hover me")
          ),
        )
      )
  })
}
  