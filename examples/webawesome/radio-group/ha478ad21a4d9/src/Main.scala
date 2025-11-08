package examples.webawesome.`radio-group`.ha478ad21a4d9
  
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
            _.label := "Select an option",
            _.hint  := "Choose the most appropriate option.",
            _.orientation.horizontal,
            _.name  := "a",
            _.value := "1"
          )(
            Radio(_.value := "1")("Option 1"),
            Radio(_.value := "2")("Option 2"),
            Radio(_.value := "3")("Option 3")
          ),
        )
      )
  })
}
  