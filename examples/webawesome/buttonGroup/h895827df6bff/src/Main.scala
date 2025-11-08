package examples.webawesome.buttonGroup.h895827df6bff
  
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
      ButtonGroup(_.orientation.vertical, _.label := "Options")(
        maxWidth.px(120),
        Button(
          _.slots.start(Icon(_.name := "plus")())
        )("New"),
        Button(
          _.slots.start(Icon(_.name := "folder-open")())
        )("Open"),
        Button(
          _.slots.start(Icon(_.name := "save")())
        )("Save"),
        Button(
          _.slots.start(Icon(_.name := "print")())
        )("Print")
      )
  })
}
  