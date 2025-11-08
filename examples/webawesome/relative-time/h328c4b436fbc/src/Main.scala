package examples.webawesome.relative-time.h328c4b436fbc
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        p("Web Awesome 2 release date 🎉"),
        RelativeTime(_.date := "2020-07-15T09:17:00-04:00")()
      )
  })
}
  