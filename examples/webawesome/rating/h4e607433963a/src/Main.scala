package examples.webawesome.rating.h4e607433963a
  
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
          Rating(
            _.label := "Rating",
            _.style := "font-size: 2rem;"
          )(),
        )
      )
  })
}
  