package examples.laminar.button.h99144601b5e3
  
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
  