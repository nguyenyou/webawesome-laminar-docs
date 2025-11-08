package examples.webawesome.format-number.h5396ad867051
  
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
          div("Default: ", FormatNumber(_.value := 3.14159)()),
          div("Min 2 digits: ", FormatNumber(_.value := 3.14159, _.minimumFractionDigits := 2.0)()),
          div("Max 2 digits: ", FormatNumber(_.value := 3.14159, _.maximumFractionDigits := 2.0)()),
          div(
            "Min 0, Max 0: ",
            FormatNumber(_.value := 3.14159, _.minimumFractionDigits := 0.0, _.maximumFractionDigits := 0.0)()
          ),
        )
      )
  })
}
  