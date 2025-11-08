package examples.webawesome.callout.hf74baecc5187
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Callout(
        _.slots.icon(Icon(_.name := "circle-info")())
      )(
        "This is a standard callout. You can customize its content and even the icon."
      )
  })
}
  