package examples.webawesome.`button-group`.hdbf71dbb4c4b
  
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
            Button()("Button"),
            Dropdown(
              _.slots.trigger(
                Button(_.withCaret := true)("Dropdown")
              )
            )(
              DropdownItem()("Item 1"),
              DropdownItem()("Item 2"),
              DropdownItem()("Item 3")
            ),
            Button()("Button")
          ),
        )
      )
  })
}
  