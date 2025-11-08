package examples.webawesome.format-number.h65a6b7be4111
  
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
          div("With grouping: ", FormatNumber(_.value := 1000000)()),
          div("Without grouping: ", FormatNumber(_.value := 1000000, _.withoutGrouping := true)()),
        )
      )
  })
}
  