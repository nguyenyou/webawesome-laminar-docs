package examples.webawesome.input.h629113a3055c
  
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
          Input(
            _.placeholder := "Small",
            _.size.small,
            _.slots.start(Icon(_.name := "house")()),
            _.slots.end(Icon(_.name := "comment")())
          )(),
          Input(
            _.placeholder := "Medium",
            _.size.medium,
            _.slots.start(Icon(_.name := "house")()),
            _.slots.end(Icon(_.name := "comment")())
          )(),
          Input(
            _.placeholder := "Large",
            _.size.large,
            _.slots.start(Icon(_.name := "house")()),
            _.slots.end(Icon(_.name := "comment")())
          )(),
        )
      )
  })
}
  