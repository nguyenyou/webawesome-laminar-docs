package examples.webawesome.badge.h069eae058d13
  
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
          Button()(
            "Requests",
            Badge(_.pill := true)("30")
          ),
          Button()(
            "Warnings",
            Badge(_.variant.warning, _.pill := true)("8")
          ),
          Button()(
            "Errors",
            Badge(_.variant.danger, _.pill := true)("6")
          ),
        )
      )
  })
}
  