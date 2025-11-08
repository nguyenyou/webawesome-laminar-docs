package examples.webawesome.textarea.had5e4d6baf8c
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Textarea(_.label := "Type something', will ya")()
  })
}
  