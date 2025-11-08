package examples.webawesome.buttonGroup.haabce34bccf7
  
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
      ButtonGroup(_.label := "Alignment")(
        Button(_.size.small, _.pill := true)("Left"),
        Button(_.size.small, _.pill := true)("Center"),
        Button(_.size.small, _.pill := true)("Right")
      )
      ButtonGroup(_.label := "Alignment")(
        Button(_.size.medium, _.pill := true)("Left"),
        Button(_.size.medium, _.pill := true)("Center"),
        Button(_.size.medium, _.pill := true)("Right")
      )
      ButtonGroup(_.label := "Alignment")(
        Button(_.size.large, _.pill := true)("Left"),
        Button(_.size.large, _.pill := true)("Center"),
        Button(_.size.large, _.pill := true)("Right")
      )
  })
}
  