package examples.webawesome.rating.h10687674578e
  
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
          Rating(_.label := "Rating", _.precision := 0.5, _.value := 2.5)(),
        )
      )
  })
}
  