package examples.webawesome.tag.h3be6d2e5ca09
  
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
        Tag(_.size.small)("Small"),
        Tag(_.size.medium)("Medium"),
        Tag(_.size.large)("Large")
      )
  })
}
  