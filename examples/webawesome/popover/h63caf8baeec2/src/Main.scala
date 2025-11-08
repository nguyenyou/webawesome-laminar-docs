package examples.webawesome.popover.h63caf8baeec2
  
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
            tw.flex.gap4.itemsCenter,
            div(
              Button(
                _.id := "popover__distance-near"
              )("Near"),
              Popover(
                _.forId    := "popover__distance-near",
                _.distance := 0
              )("I'm very close")
            ),
            div(
              Button(
                _.id := "popover__distance-far"
              )("Far"),
              Popover(
                _.forId    := "popover__distance-far",
                _.distance := 30
              )("I'm farther away")
            )
          ),
        )
      )
  })
}
  