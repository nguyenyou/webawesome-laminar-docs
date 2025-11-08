package examples.webawesome.`format-bytes`.h714ea8ff2632
  
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
          FormatBytes(_.value := 1200000, _.display.long)(),
          FormatBytes(_.value := 1200000, _.display.short)(),
          FormatBytes(_.value := 1200000, _.display.narrow)(),
        )
      )
  })
}
  