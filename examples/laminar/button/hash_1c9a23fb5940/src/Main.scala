package examples.laminar.button.hash_1c9a23fb5940
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        color := "blue",
        "Hohohoho"
      )
  })
}
  