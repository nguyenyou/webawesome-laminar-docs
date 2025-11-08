package examples.webawesome.tooltip.hbb2e0dc8f3a2
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        Tooltip(
          _.forId := "tooltip-trigger"
        )("This is a tooltip"),
        Button(
          _.id := "tooltip-trigger"
        )("Hover Me")
      )
  })
}
  