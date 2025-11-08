package examples.webawesome.popover.h3dfe2726821a
  
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
                _.id := "popover__top"
              )("Top"),
              Popover(
                _.forId     := "popover__top",
                _.placement := "top"
              )("I'm on the top")
            ),
            div(
              Button(
                _.id := "popover__bottom"
              )("Bottom"),
              Popover(
                _.forId     := "popover__bottom",
                _.placement := "bottom"
              )("I'm on the bottom")
            ),
            div(
              Button(
                _.id := "popover__left"
              )("Left"),
              Popover(
                _.forId     := "popover__left",
                _.placement := "left"
              )("I'm on the left")
            ),
            div(
              Button(
                _.id := "popover__right"
              )("Right"),
              Popover(
                _.forId     := "popover__right",
                _.placement := "right"
              )("I'm on the right")
            )
          ),
        )
      )
  })
}
  