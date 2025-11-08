package examples.webawesome.`format-bytes`.h00285088bd4b
  
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
          FormatBytes(_.value := 12, _.unit.bit)(),
          FormatBytes(_.value := 1200, _.unit.bit)(),
          FormatBytes(_.value := 1200000, _.unit.bit)(),
          FormatBytes(_.value := 1200000000, _.unit.bit)(),
        )
      )
  })
}
  