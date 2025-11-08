package examples.webawesome.dropdown.ha97c5e693a40
  
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
            _.onSelect.map { event =>
              event.detail.item.`type`.toOption match {
                case Some("checkbox") =>
                  println(event.detail.item.value.toOption)
                  println(event.detail.item.checked.toOption)
                case _ =>
                  println(event.detail.item.value.toOption)
              }
            } --> Observer.empty,
            _.slots.trigger(
              Button(_.withCaret := true)("View")
            )
          )(
            DropdownItem(_.`type`.checkbox, _.value := "canvas", _.checked := true)("Show canvas"),
            DropdownItem(_.`type`.checkbox, _.value := "grid", _.checked := true)("Show grid"),
            DropdownItem(_.`type`.checkbox, _.value := "source")("Show source"),
            Divider()(),
            DropdownItem(_.value := "preferences")("Preferences…")
          ),
        )
      )
  })
}
  