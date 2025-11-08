package examples.webawesome.dropdown.h190b99bcae15
  
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
              Button(_.withCaret := true)("Edit")
            )
          )(
            DropdownItem(_.value := "cut", _.slots.icon(Icon(_.name := "scissors")()))("Cut"),
            DropdownItem(_.value := "copy", _.slots.icon(Icon(_.name := "copy")()))("Copy"),
            DropdownItem(_.value := "paste", _.slots.icon(Icon(_.name := "paste")()))("Paste"),
            DropdownItem(_.value := "delete", _.slots.icon(Icon(_.name := "trash")()))("Delete")
          ),
        )
      )
  })
}
  