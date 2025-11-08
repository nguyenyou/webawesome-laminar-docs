package examples.webawesome.details.h2397a6c961f1
  
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
          Details(
            _.slots.summary(
              span(
                "Some text ",
                a(href := "https://webawesome.com", target := "_blank", "a link"),
                " more text"
              )
            )
          )(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
          ),
        )
      )
  })
}
  