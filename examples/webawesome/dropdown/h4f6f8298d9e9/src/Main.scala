package examples.webawesome.dropdown.h4f6f8298d9e9
  
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
              Button(_.withCaret := true)("Payment method")
            )
          )(
            DropdownItem(_.value := "cash")("Cash"),
            DropdownItem(_.value := "check", _.disabled := true)("Personal check"),
            DropdownItem(_.value := "credit")("Credit card"),
            DropdownItem(_.value := "gift-card")("Gift card")
          ),
        )
      )
  })
}
  