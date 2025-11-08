package examples.webawesome.`relative-time`.h4e66a021cd3e
  
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
          div("Narrow: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.narrow)()),
          div("Short: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.short)()),
          div("Long: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00", _.format.long)()),
        )
      )
  })
}
  