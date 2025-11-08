package examples.webawesome.dropdown.h3c60d4f75d15
  
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
          Dropdown(
            _.slots.trigger(
              Button(_.withCaret := true)("Device")
            )
          )(
            h3("Type"),
            DropdownItem(_.value := "phone")("Phone"),
            DropdownItem(_.value := "tablet")("Tablet"),
            DropdownItem(_.value := "desktop")("Desktop"),
            Divider()(),
            DropdownItem(_.value := "more")("More options...")
          ),
        )
      )
  })
}
  