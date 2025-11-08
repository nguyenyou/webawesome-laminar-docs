package examples.webawesome.select.ha6cb981dae51
  
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
          Select(
            _.withClear := true,
            _.value     := "option-1"
          )(
            UOption(_.value := "option-1")("Option 1"),
            UOption(_.value := "option-2")("Option 2"),
            UOption(_.value := "option-3")("Option 3")
          ),
        )
      )
  })
}
  