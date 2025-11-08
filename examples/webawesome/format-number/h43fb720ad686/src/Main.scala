package examples.webawesome.format-number.h43fb720ad686
  
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
          FormatNumber(_.`type`.percent, _.value := 0)(),
          FormatNumber(_.`type`.percent, _.value := 0.25)(),
          FormatNumber(_.`type`.percent, _.value := 0.50)(),
          FormatNumber(_.`type`.percent, _.value := 0.75)(),
          FormatNumber(_.`type`.percent, _.value := 1)(),
        )
      )
  })
}
  