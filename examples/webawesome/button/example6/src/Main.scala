package examples.webawesome.button.example6
  
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
          Button(_.href := "https://example.com/")("Link"),
          Button(_.href := "https://example.com/", _.target := "_blank")("Link with target"),
          Button(
            _.href     := "/assets/images/logo.svg",
            _.download := "shoelace.svg"
          )("Download"),
        )
      )
  })
}
  