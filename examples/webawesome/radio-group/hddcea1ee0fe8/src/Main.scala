package examples.webawesome.`radio-group`.hddcea1ee0fe8
  
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
          RadioGroup(
            _.label := "Horizontal options",
            _.hint  := "Select an option that makes you proud.",
            _.orientation.horizontal,
            _.name  := "a",
            _.value := "1"
          )(
            Radio(_.appearance.button, _.value := "1")("Option 1"),
            Radio(_.appearance.button, _.value := "2")("Option 2"),
            Radio(_.appearance.button, _.value := "3")("Option 3")
          ),
          RadioGroup(
            _.label := "Vertical options",
            _.hint  := "Select an option that makes you proud.",
            _.orientation.vertical,
            _.name  := "a",
            _.value := "1"
          )(
            maxWidth.px(300),
            Radio(_.appearance.button, _.value := "1")("Option 1"),
            Radio(_.appearance.button, _.value := "2")("Option 2"),
            Radio(_.appearance.button, _.value := "3")("Option 3")
          ),
        )
      )
  })
}
  