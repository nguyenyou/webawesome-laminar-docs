package examples.webawesome.`qr-code`.h43f7260a340a
  
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
            _.value  := "https://shoelace.style/",
            _.radius := 0.5
          )(),
        )
      )
  })
}
  