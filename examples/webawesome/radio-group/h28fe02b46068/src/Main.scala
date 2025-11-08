package examples.webawesome.radio-group.h28fe02b46068
  
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
          RadioGroup(_.label := "Select an option")(
            Radio(_.value := "1")("Option 1"),
            Radio(_.value := "2", _.disabled := true)("Option 2"),
            Radio(_.value := "3")("Option 3")
          ),
        )
      )
  })
}
  