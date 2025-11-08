package examples.webawesome.progress-bar.h7e1a950097a3
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ProgressBar(_.value := 40)()
  })
}
  