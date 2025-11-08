package examples.webawesome.`format-date`.hd50a4240895f
  
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
          div("12-hour: ", FormatDate(_.hour.numeric, _.minute.numeric, _.hourFormat := "12")()),
          div("24-hour: ", FormatDate(_.hour.numeric, _.minute.numeric, _.hourFormat := "24")()),
        )
      )
  })
}
  