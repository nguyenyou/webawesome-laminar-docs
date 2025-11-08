package examples.laminar_button_4462db330ec2
  
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
  