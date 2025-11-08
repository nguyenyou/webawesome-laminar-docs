package examples.hash_9e318d656792
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.size.small)("Small"),
        Button(_.size.medium)("Medium"),
        Button(_.size.large)("Large"),
      )
  })
}
  