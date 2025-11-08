package examples.webawesome.button-group.hf7c60baff6e8
  
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
          ButtonGroup(_.label := "Example Button Group")(
            Button(_.variant.brand)("Save"),
            Dropdown(
              _.placement.bottomEnd,
              _.slots.trigger(
                Button(_.variant.brand)(
                  Icon(_.name := "chevron-down", _.label := "More options")() // [!code highlight]
                )
              )
            )(
              DropdownItem()("Save"),
              DropdownItem()("Save as…"),
              DropdownItem()("Save all")
            )
          ),
        )
      )
  })
}
  