package examples.webawesome.format-date.h66b60d5e6a34
  
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
          div("UTC: ", FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "UTC")()),
          div(
            "Pacific: ",
            FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "America/Los_Angeles")()
          ),
          div("Tokyo: ", FormatDate(_.hour.numeric, _.minute.numeric, _.timeZone := "Asia/Tokyo")()),
        )
      )
  })
}
  