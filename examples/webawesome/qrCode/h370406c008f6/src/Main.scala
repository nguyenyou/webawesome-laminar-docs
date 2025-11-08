package examples.webawesome.qrCode.h370406c008f6
  
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
      val qrValue = Var("https://shoelace.style/")
      
      div(
        maxWidth.px(256),
        QrCode(
          _.value <-- qrValue.signal,
          _.label := "Scan this code to visit Web Awesome on the web!"
        )(),
        br(),
        Input(
          _.maxlength := 255.0,
          _.withClear := true,
          _.label     := "Value",
          _.value <-- qrValue.signal,
          _.slots.start(Icon(_.name := "link")())
        )(
          onInput.mapToValue --> qrValue.writer
        )
      )
  })
}
  