package examples.webawesome.buttonGroup.h85cee85a2367
  
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
      ButtonGroup(_.size.small, _.label := "Alignment")(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.size.medium, _.label := "Alignment")(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
      ButtonGroup(_.size.large, _.label := "Alignment")(
        Button()("Left"),
        Button()("Center"),
        Button()("Right")
      )
  })
}
  