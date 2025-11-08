package examples.webawesome.input.h33024313d8f4
  
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
          Input(
            _.placeholder := "Small",
            _.size.small,
            _.pill := true
          )(),
          Input(
            _.placeholder := "Medium",
            _.size.medium,
            _.pill := true
          )(),
          Input(
            _.placeholder := "Large",
            _.size.large,
            _.pill := true
          )(),
        )
      )
  })
}
  