package examples.webawesome.dropdown.h542c10cd4c9f
  
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
            _.onSelect.map(_.detail.item.value.toOption) --> Observer[Option[String]](println),
            _.slots.trigger(
              Button(_.withCaret := true)("View")
            )
          )(
            DropdownItem(_.value := "full-screen")("Enter full screen"),
            DropdownItem(_.value := "actual")("Actual size"),
            DropdownItem(_.value := "zoom-in")("Zoom in"),
            DropdownItem(_.value := "zoom-out")("Zoom out")
          ),
        )
      )
  })
}
  