package examples.webawesome.`radio-group`.hfa557b40fb9f
  
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
            _.size.small
          )(
            Radio(_.value := "small")("Small"),
            Radio(_.value := "medium")("Medium"),
            Radio(_.value := "large")("Large")
          ),
          RadioGroup(
            _.size.medium
          )(
            Radio(_.value := "small")("Small"),
            Radio(_.value := "medium")("Medium"),
            Radio(_.value := "large")("Large")
          ),
          RadioGroup(
            _.size.large
          )(
            Radio(_.value := "small")("Small"),
            Radio(_.value := "medium")("Medium"),
            Radio(_.value := "large")("Large")
          ),
        )
      )
  })
}
  