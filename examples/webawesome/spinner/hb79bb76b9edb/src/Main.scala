package examples.webawesome.spinner.hb79bb76b9edb
  
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
          Spinner(
            _.style := "font-size: 50px; --track-width: 10px;"
          )(),
        )
      )
  })
}
  