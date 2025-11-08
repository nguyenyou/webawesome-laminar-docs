package examples.webawesome.input.hd41c62289ba5
  
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
            _.typ.email,
            _.placeholder := "Email"
          )(),
          Input(
            _.typ.number,
            _.placeholder := "Number"
          )(),
          Input(
            _.typ.date,
            _.placeholder := "Date"
          )(),
        )
      )
  })
}
  