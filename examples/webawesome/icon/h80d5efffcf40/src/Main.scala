package examples.webawesome.icon.h80d5efffcf40
  
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
          Icon(
            _.src   := "https://shoelace.style/assets/images/shoe.svg",
            _.style := "font-size: 4rem;"
          )(),
        )
      )
  })
}
  