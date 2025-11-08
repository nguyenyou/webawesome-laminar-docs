package examples.webawesome.spinner.h4365bbe31779
  
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
            _.style := "font-size: 3rem; --indicator-color: deeppink; --track-color: pink;"
          )(),
        )
      )
  })
}
  