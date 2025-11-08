package examples.webawesome.input.h71b40d5943b3
  
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
            _.placeholder := "Disabled"
          )(
            disabled := true
          ),
        )
      )
  })
}
  