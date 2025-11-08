package examples.webawesome.dropdown.ha0bcfd8c84cf
  
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
            _.skidding := 30.0,
            _.slots.trigger(
              Button(_.withCaret := true)("Edit")
            )
          )(
            DropdownItem()("Cut"),
            DropdownItem()("Copy"),
            DropdownItem()("Paste"),
            Divider()(),
            DropdownItem()("Find"),
            DropdownItem()("Replace")
          ),
        )
      )
  })
}
  