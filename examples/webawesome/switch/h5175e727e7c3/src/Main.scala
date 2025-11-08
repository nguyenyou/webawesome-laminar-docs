package examples.webawesome.switch.h5175e727e7c3
  
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
          Switch(
            _.style := "--width: 80px; --height: 40px; --thumb-size: 36px;"
          )("Really big"),
        )
      )
  })
}
  