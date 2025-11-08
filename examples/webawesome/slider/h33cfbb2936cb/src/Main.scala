package examples.webawesome.slider.h33cfbb2936cb
  
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
          Slider(
            _.label    := "Required slider",
            _.min      := 0,
            _.max      := 10,
            _.required := true // [!code highlight]
          )(),
        )
      )
  })
}
  