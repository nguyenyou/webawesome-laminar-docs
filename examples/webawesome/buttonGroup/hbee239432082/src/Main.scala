package examples.webawesome.buttonGroup.hbee239432082
  
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
      div(
        ButtonGroup(_.label := "Alignment")(
          Button(_.id := "button-left")("Left"),
          Button(_.id := "button-center")("Center"),
          Button(_.id := "button-right")("Right")
        ),
        Tooltip(_.forId := "button-left")("I'm on the left"),
        Tooltip(_.forId := "button-center")("I'm in the middle"),
        Tooltip(_.forId := "button-right")("I'm on the right")
      )
  })
}
  