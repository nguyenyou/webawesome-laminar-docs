package examples.webawesome.icon.example3
  
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
        color := "#4a90e2",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "exclamation-triangle")(),
        Icon(_.name := "archive")(),
        Icon(_.name := "battery-three-quarters")(),
        Icon(_.name := "bell")()
      )
      div(
        color := "#9013fe",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "clock")(),
        Icon(_.name := "cloud")(),
        Icon(_.name := "download")(),
        Icon(_.name := "file")()
      )
      div(
        color := "#417505",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "flag")(),
        Icon(_.name := "heart")(),
        Icon(_.name := "image")(),
        Icon(_.name := "bolt-lightning")()
      )
      div(
        color := "#f5a623",
        cls("flex gap-2"),
        Icon(_.name := "microphone")(),
        Icon(_.name := "search")(),
        Icon(_.name := "star")(),
        Icon(_.name := "trash")()
      )
  })
}
  