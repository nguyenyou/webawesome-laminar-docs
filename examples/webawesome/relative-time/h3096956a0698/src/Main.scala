package examples.webawesome.relative-time.h3096956a0698
  
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
          div("English: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "en-US")),
          div("Chinese: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "zh-CN")),
          div("German: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "de")),
          div("Greek: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "el")),
          div("Russian: ", RelativeTime(_.date := "2020-07-15T09:17:00-04:00")(lang := "ru")),
        )
      )
  })
}
  