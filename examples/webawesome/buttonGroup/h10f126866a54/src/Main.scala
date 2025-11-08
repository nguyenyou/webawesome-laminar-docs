package examples.webawesome.buttonGroup.h10f126866a54
  
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
      ButtonGroup(_.label := "Alignment", _.variant.brand)(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.label := "Alignment", _.variant.success)(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.label := "Alignment")(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.label := "Alignment", _.variant.warning)(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.label := "Alignment", _.variant.danger)(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
  })
}
  