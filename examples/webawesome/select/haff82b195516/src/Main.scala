package examples.webawesome.select.haff82b195516
  
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
          Select()(
            small("Section 1"),
            UOption(_.value := "option-1")("Option 1"),
            UOption(_.value := "option-2")("Option 2"),
            UOption(_.value := "option-3")("Option 3"),
            Divider()(),
            small("Section 2"),
            UOption(_.value := "option-4")("Option 4"),
            UOption(_.value := "option-5")("Option 5"),
            UOption(_.value := "option-6")("Option 6")
          ),
        )
      )
  })
}
  