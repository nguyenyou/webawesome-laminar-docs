package examples.webawesome.relative-time.h76da2cfcbe39
  
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
          RelativeTime(
            _.sync := true,
            _.date := {
              // Set the date to 1 minute ago
              val oneMinuteAgo = new js.Date(js.Date.now() - 60000)
              oneMinuteAgo.toISOString()
            }
          )(),
        )
      )
  })
}
  