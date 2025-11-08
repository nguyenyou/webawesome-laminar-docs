package examples.webawesome.`format-bytes`.hee5bc69bb9d9
  
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
          FormatBytes(_.value := 12)(),
          FormatBytes(_.value := 1200)(),
          FormatBytes(_.value := 1200000)(),
          FormatBytes(_.value := 1200000000)(),
        )
      )
  })
}
  