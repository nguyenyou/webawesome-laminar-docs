package examples.webawesome.rating.h073cfec922aa
  
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
          Rating(_.label := "Rating", _.disabled := true, _.value := 3)(),
        )
      )
  })
}
  