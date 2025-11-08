package examples.webawesome.tooltip.hdc665f75c3c8
  
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
      val openVar = Var(false)
      div(
        Button()(
          onClick --> Observer { _ =>
            openVar.update(!_)
          },
          marginRight.rem(4),
          "Toggle Manually"
        ),
        Tooltip(
          _.open <-- openVar,
          _.forId   := "manual-trigger-tooltip",
          _.trigger := "manual"
        )("This is an avatar!"),
        Avatar(_.id := "manual-trigger-tooltip")()
      )
  })
}
  