package examples.webawesome.icon.h789982619cbc
  
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
            _.name := "eyedropper"
          )(),
          Icon(
            _.name := "grip-vertical"
          )(),
          Icon(
            _.name := "play"
          )(),
          Icon(
            _.name := "star"
          )(),
          Icon(
            _.name := "user"
          )(),
        )
      )
  })
}
  