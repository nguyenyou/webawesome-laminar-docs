package examples.webawesome.input.h30fad913c0be
  
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
            _.size.small
          )(),
          Input(
            _.placeholder := "Medium",
            _.size.medium
          )(),
          Input(
            _.placeholder := "Large",
            _.size.large
          )(),
        )
      )
  })
}
  