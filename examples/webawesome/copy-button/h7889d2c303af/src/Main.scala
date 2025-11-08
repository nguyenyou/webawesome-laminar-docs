package examples.webawesome.`copy-button`.h7889d2c303af
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      CopyButton(
        _.value := "Web Awesome rocks!"
      )()
  })
}
  