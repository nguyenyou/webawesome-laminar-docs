package examples.webawesome.input.h32b63cda7116
  
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
            _.typ            := "password",
            _.placeholder    := "Password Toggle",
            _.passwordToggle := true
          )(),
        )
      )
  })
}
  