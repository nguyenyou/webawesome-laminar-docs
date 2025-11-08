package examples.webawesome.`copy-button`.hc7112ccd3ffc
  
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
            _.value        := "Custom labels are easy",
            _.copyLabel    := "Click to copy",
            _.successLabel := "You did it!",
            _.errorLabel   := "Whoops, your browser doesn't support this!"
          )(),
        )
      )
  })
}
  