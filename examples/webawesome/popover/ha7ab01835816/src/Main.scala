package examples.webawesome.popover.ha7ab01835816
  
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
                _.id := "popover__big-arrow"
              )("Big arrow"),
              Popover(
                _.forId := "popover__big-arrow",
                _.style := "--arrow-size: 8px;"
              )("I have a big arrow")
            ),
            div(
              Button(
                _.id := "popover__no-arrow"
              )("No arrow"),
              Popover(
                _.forId := "popover__no-arrow",
                _.style := "--arrow-size: 0;"
              )("I don't have an arrow")
            )
          ),
        )
      )
  })
}
  