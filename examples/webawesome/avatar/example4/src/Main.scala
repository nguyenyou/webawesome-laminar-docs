package examples.webawesome.avatar.example4
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Avatar(
        _.label := "Avatar with an image icon",
        // [!code highlight:6]
        _.slots.icon(
          Icon(
            _.name    := "image",
            _.variant := "solid"
          )()
        )
      )()
      Avatar(
        _.label := "Avatar with an archive icon",
        _.slots.icon(
          Icon(
            _.name    := "archive",
            _.variant := "solid"
          )()
        )
      )()
      Avatar(
        _.label := "Avatar with a briefcase icon",
        _.slots.icon(
          Icon(
            _.name    := "briefcase",
            _.variant := "solid"
          )()
        )
      )()
  })
}
  