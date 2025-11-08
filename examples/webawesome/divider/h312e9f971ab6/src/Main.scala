package examples.webawesome.divider.h312e9f971ab6
  
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
        textAlign.center,
        "Above",
        Divider(
          _.style := "--spacing: 2rem;" // [!code highlight]
        )(),
        "Below"
      )
  })
}
  