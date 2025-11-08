package examples.webawesome.format-number.he82673cb66b4
  
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
          div("USD: ", FormatNumber(_.`type`.currency, _.currency := "USD", _.value := 2000)()),
          div("GBP: ", FormatNumber(_.`type`.currency, _.currency := "GBP", _.value := 2000)()),
          div("EUR: ", FormatNumber(_.`type`.currency, _.currency := "EUR", _.value := 2000)()),
          div("JPY: ", FormatNumber(_.`type`.currency, _.currency := "JPY", _.value := 2000)()),
          div("CNY: ", FormatNumber(_.`type`.currency, _.currency := "CNY", _.value := 2000)()),
        )
      )
  })
}
  