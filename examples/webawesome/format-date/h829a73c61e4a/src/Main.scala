package examples.webawesome.format-date.h829a73c61e4a
  
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
          div("Human-readable date: ", FormatDate(_.month.long, _.day.numeric, _.year.numeric)()),
          div("Time: ", FormatDate(_.hour.numeric, _.minute.numeric)()),
          div("Weekday: ", FormatDate(_.weekday.long)()),
          div("Month: ", FormatDate(_.month.long)()),
          div("Year: ", FormatDate(_.year.numeric)()),
          div("No formatting options: ", FormatDate()()),
        )
      )
  })
}
  