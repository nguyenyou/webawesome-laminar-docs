package examples.webawesome.popover.h0178e27adda9
  
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
            tw.flex.flexCol.itemsStart.gap4,
            div(
              Popover(
                _.forId := "popover__anchor-button"
              )(
                "I'm anchored to a Web Awesome button."
              ),
              Button(
                _.id := "popover__anchor-button"
              )("Show popover")
            ),
            div(
              Popover(
                _.forId := "popover__anchor-native-button"
              )(
                "I'm anchored to a native button."
              ),
              button(
                idAttr := "popover__anchor-native-button",
                "Show Popover"
              )
            )
          ),
        )
      )
  })
}
  