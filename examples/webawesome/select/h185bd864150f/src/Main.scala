package examples.webawesome.select.h185bd864150f
  
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
            _.label     := "Select a Few",
            _.multiple  := true,
            _.withClear := true
          )(
            UOption(_.value := "option-1", _.selected := true)("Option 1"),
            UOption(_.value := "option-2", _.selected := true)("Option 2"),
            UOption(_.value := "option-3", _.selected := true)("Option 3"),
            UOption(_.value := "option-4")("Option 4"),
            UOption(_.value := "option-5")("Option 5"),
            UOption(_.value := "option-6")("Option 6")
          ),
        )
      )
  })
}
  