package examples.webawesome.dropdown.hef26d5050f51
  
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
              Button(_.withCaret := true)("Project")
            )
          )(
            DropdownItem(
              _.value := "share",
              _.slots.icon(
                Icon(_.name := "share")()
              )
            )("Share"),
            DropdownItem(
              _.value := "preferences",
              _.slots.icon(
                Icon(_.name := "gear")()
              )
            )("Preferences"),
            Divider()(),
            h3("Danger zone"),
            DropdownItem(
              _.value := "archive",
              _.slots.icon(
                Icon(_.name := "archive")()
              )
            )("Archive"),
            DropdownItem(
              _.value   := "delete",
              _.variant := "danger",
              _.slots.icon(
                Icon(_.name := "trash")()
              )
            )("Delete")
          ),
        )
      )
  })
}
  