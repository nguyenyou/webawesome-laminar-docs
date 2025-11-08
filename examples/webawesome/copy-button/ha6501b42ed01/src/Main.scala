package examples.webawesome.`copy-button`.ha6501b42ed01
  
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
          CopyButton(
            _.value := "Copied from a custom button",
            _.slots.copyIcon(Icon(_.name := "clipboard", _.variant := "regular")()),
            _.slots.successIcon(Icon(_.name := "thumbs-up", _.variant := "solid")()),
            _.slots.errorIcon(Icon(_.name := "xmark", _.variant := "solid")())
          )(),
        )
      )
  })
}
  