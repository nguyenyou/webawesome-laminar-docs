package examples.webawesome.formatBytes.example1
  
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
      val valueVar = Var("1000")
      
      div(
        p("The file is ", FormatBytes(_.value <-- valueVar.signal.map(_.toDouble))(), " in size."),
        Input(
          _.`type`.number,
          _.value <-- valueVar,
          _.label := "Number to Format"
        )(
          onInput.mapToValue --> valueVar,
          maxWidth.px(180)
        )
      )
  })
}
  