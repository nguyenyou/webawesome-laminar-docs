package examples.webawesome.avatar.hcd1e8a3d315f
  
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
          Avatar(
            _.label := "Avatar with an image icon",
            // [!code highlight:6]
            _.slots.icon(
              Icon(
                _.name    := "image",
                _.variant := "solid"
              )()
            )
          )(),
          Avatar(
            _.label := "Avatar with an archive icon",
            _.slots.icon(
              Icon(
                _.name    := "archive",
                _.variant := "solid"
              )()
            )
          )(),
          Avatar(
            _.label := "Avatar with a briefcase icon",
            _.slots.icon(
              Icon(
                _.name    := "briefcase",
                _.variant := "solid"
              )()
            )
          )(),
        )
      )
  })
}
  