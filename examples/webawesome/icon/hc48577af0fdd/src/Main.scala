package examples.webawesome.icon.hc48577af0fdd
  
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
          Icon(
            _.fixedWidth := "true",
            _.name       := "cloud"
          )(),
          Icon(
            _.fixedWidth := "true",
            _.name       := "user"
          )(),
          Icon(
            _.fixedWidth := "true",
            _.name       := "truck"
          )(),
          Icon(
            _.fixedWidth := "true",
            _.name       := "file"
          )(),
          Icon(
            _.fixedWidth := "true",
            _.name       := "skating"
          )(),
          Icon(
            _.fixedWidth := "true",
            _.name       := "snowplow"
          )(),
        )
      )
  })
}
  