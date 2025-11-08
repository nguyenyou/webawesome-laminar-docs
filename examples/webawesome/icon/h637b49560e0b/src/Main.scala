package examples.webawesome.icon.h637b49560e0b
  
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
        fontSize := "32px",
        cls("flex gap-2"),
        Icon(_.name := "bell")(),
        Icon(_.name := "heart")(),
        Icon(_.name := "image")(),
        Icon(_.name := "microphone")(),
        Icon(_.name := "search")(),
        Icon(_.name := "star")()
      )
  })
}
  