package examples.webawesome.input.h1d7f9005aa5b
  
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
            _.placeholder := "Type something"
          )(),
        )
      )
  })
}
  