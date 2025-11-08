package examples.webawesome.select.hbc2aaebb3f32
  
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
            _.placeholder := "Small",
            _.size.small,
            _.withClear := true,
            _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
            _.slots.end(Icon(_.name := "flag-checkered")())
          )(
            UOption(_.value := "option-1")("Option 1"),
            UOption(_.value := "option-2")("Option 2"),
            UOption(_.value := "option-3")("Option 3")
          ),
          Select(
            _.placeholder := "Medium",
            _.size.medium,
            _.withClear := true,
            _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
            _.slots.end(Icon(_.name := "flag-checkered")())
          )(
            UOption(_.value := "option-1")("Option 1"),
            UOption(_.value := "option-2")("Option 2"),
            UOption(_.value := "option-3")("Option 3")
          ),
          Select(
            _.placeholder := "Large",
            _.size.large,
            _.withClear := true,
            _.slots.start(Icon(_.name := "house", _.variant := "solid")()),
            _.slots.end(Icon(_.name := "flag-checkered")())
          )(
            UOption(_.value := "option-1")("Option 1"),
            UOption(_.value := "option-2")("Option 2"),
            UOption(_.value := "option-3")("Option 3")
          ),
        )
      )
  })
}
  