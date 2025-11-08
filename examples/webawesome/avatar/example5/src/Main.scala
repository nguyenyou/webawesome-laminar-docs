package examples.webawesome.avatar.example5
  
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
        _.shape.square, // [!code highlight]
        _.label := "Square avatar"
      )()
      Avatar(
        _.shape.rounded, // [!code highlight]
        _.label := "Rounded avatar"
      )()
      Avatar(
        _.shape.circle, // [!code highlight]
        _.label := "Circle avatar"
      )()
  })
}
  