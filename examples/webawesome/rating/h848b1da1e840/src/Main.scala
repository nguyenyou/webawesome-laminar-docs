package examples.webawesome.rating.h848b1da1e840
  
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
          Rating(_.label := "Rating", _.size.small)(),
          Rating(_.label := "Rating", _.size.medium)(),
          Rating(_.label := "Rating", _.size.large)(),
        )
      )
  })
}
  