package examples.webawesome.divider.he5d3d9c61080
  
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
              Button(
                _.withCaret := true
              )("Menu")
            ),
            _.style := "max-width: 200px;" // [!code highlight]
          )(
            DropdownItem(
              _.value := "1"
            )("Option 1"),
            DropdownItem(
              _.value := "2"
            )("Option 2"),
            DropdownItem(
              _.value := "3"
            )("Option 3"),
            Divider()(), // [!code highlight]
            DropdownItem(
              _.value := "4"
            )("Option 4"),
            DropdownItem(
              _.value := "5"
            )("Option 5"),
            DropdownItem(
              _.value := "6"
            )("Option 6")
          ),
        )
      )
  })
}
  