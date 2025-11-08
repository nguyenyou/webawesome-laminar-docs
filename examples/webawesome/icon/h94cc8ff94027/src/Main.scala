package examples.webawesome.icon.h94cc8ff94027
  
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
            _.name  := "star",
            _.label := "Add to favorites"
          )(),
        )
      )
  })
}
  