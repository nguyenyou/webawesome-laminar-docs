package examples.webawesome.tag.example5
  
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
        div(
          cls("flex flex-wrap gap-2"),
          Tag(_.size.small, _.withRemove := true)("Small"),
          Tag(_.size.medium, _.withRemove := true)("Medium"),
          Tag(_.size.large, _.withRemove := true)("Large")
        )
      )
  })
}
  