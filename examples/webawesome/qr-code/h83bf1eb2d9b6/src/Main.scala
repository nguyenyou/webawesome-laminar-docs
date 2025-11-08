package examples.webawesome.`qr-code`.h83bf1eb2d9b6
  
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
          QrCode(
            _.value      := "https://shoelace.style/",
            _.fill       := "deeppink",
            _.background := "white"
          )(),
        )
      )
  })
}
  