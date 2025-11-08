package examples.webawesome.icon.he135e60bac41
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Icon(
        _.name := "star"
      )()
  })
}
  