package examples.webawesome.format-date.had6f611f7c8d
  
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
        FormatDate(_.date := "2020-07-15T09:17:00-04:00")()
      )
  })
}
  