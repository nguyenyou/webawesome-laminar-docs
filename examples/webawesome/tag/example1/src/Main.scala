package examples.webawesome.tag.example1
  
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
        cls("flex flex-wrap gap-2"),
        Tag(_.variant.brand)("Brand"),
        Tag(_.variant.success)("Success"),
        Tag(_.variant.neutral)("Neutral"),
        Tag(_.variant.warning)("Warning"),
        Tag(_.variant.danger)("Danger")
      )
  })
}
  