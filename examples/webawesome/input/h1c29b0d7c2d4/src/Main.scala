package examples.webawesome.input.h1c29b0d7c2d4
  
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
            _.label := "Nickname",
            _.hint  := "What would you like people to call you?"
          )(),
        )
      )
  })
}
  