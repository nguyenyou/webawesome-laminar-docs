package examples.webawesome.qr-code.h535db465d380
  
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
            _.value           := "https://shoelace.style/",
            _.errorCorrection := "L"
          )(),
          QrCode(
            _.value           := "https://shoelace.style/",
            _.errorCorrection := "M"
          )(),
          QrCode(
            _.value           := "https://shoelace.style/",
            _.errorCorrection := "Q"
          )(),
          QrCode(
            _.value           := "https://shoelace.style/",
            _.errorCorrection := "H"
          )(),
        )
      )
  })
}
  