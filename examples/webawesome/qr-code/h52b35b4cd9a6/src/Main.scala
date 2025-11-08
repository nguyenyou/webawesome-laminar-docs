package examples.webawesome.qr-code.h52b35b4cd9a6
  
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
            _.value := "https://shoelace.style/",
            _.size  := 64.0
          )(),
        )
      )
  })
}
  