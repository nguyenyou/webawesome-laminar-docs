package examples.webawesome.rating.hf49c51ad3423
  
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
          Rating(_.label := "Rating", _.max := 3)(),
        )
      )
  })
}
  