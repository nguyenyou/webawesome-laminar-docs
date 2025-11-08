package examples.webawesome.button.hash_ae20511d31d5
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(_.variant.brand)("Brand"),
        Button(_.variant.danger)("Danger"),
        Button(_.variant.neutral)("Neutral"),
        Button(_.variant.success)("Success"),
        Button(_.variant.warning)("Warning"),
      )
  })
}
  