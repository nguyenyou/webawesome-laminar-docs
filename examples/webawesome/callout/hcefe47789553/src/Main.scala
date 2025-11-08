package examples.webawesome.callout.hcefe47789553
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ExampleGroups(
        Examples(
          Callout(_.variant.brand)(
            "Nothing fancy here, just a simple callout."
          ),
        )
      )
  })
}
  