package examples.webawesome.popover.example7
  
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
        Button(
          _.id := "popover__max-width"
        )("Toggle me"),
        Popover(
          _.forId := "popover__max-width",
          _.style := "--max-width: 160px;"
        )(
          "Popovers will usually grow to be much wider, but this one has a custom max width that forces text to wrap."
        )
      )
  })
}
  