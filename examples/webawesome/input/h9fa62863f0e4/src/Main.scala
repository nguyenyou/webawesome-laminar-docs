package examples.webawesome.input.h9fa62863f0e4
  
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
            _.placeholder := "Clearable",
            _.withClear   := true
          )(),
        )
      )
  })
}
  