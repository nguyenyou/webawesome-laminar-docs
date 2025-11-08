package examples.webawesome.include.h8c9bb08e1ca8
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Include(
        _.src := "https://shoelace.style/assets/examples/include.html"
      )()
  })
}
  