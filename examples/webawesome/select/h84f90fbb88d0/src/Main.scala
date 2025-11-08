package examples.webawesome.select.h84f90fbb88d0
  
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
            _.label := "Experience",
            _.hint  := "Please tell us your skill level."
          )(
            UOption(_.value := "1")("Novice"),
            UOption(_.value := "2")("Intermediate"),
            UOption(_.value := "3")("Advanced")
          ),
        )
      )
  })
}
  