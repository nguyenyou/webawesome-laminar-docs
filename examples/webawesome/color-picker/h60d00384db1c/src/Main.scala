package examples.webawesome.color-picker.h60d00384db1c
  
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
          ColorPicker(
            _.value   := "#f5a623ff",
            _.opacity := true,
            _.label   := "Select a color"
          )(),
        )
      )
  })
}
  