package examples.webawesome.radio-group.h91b9b37b17a8
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      RadioGroup(_.label := "Select an option", _.name := "a", _.value := "1")(
        Radio(_.value := "1")("Option 1"),
        Radio(_.value := "2")("Option 2"),
        Radio(_.value := "3")("Option 3")
      )
  })
}
  