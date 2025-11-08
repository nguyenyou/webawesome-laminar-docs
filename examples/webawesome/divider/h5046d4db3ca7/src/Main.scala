package examples.webawesome.divider.h5046d4db3ca7
  
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
          div(
            textAlign.center,
            "Above",
            Divider(
              _.style := "--spacing: 2rem;" // [!code highlight]
            )(),
            "Below"
          ),
        )
      )
  })
}
  