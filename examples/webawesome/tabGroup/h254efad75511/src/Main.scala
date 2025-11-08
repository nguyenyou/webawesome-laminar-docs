package examples.webawesome.tabGroup.h254efad75511
  
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
      TabGroup(_.placement.end)(
        Tab(_.panel := "general")("General"),
        Tab(_.panel := "custom")("Custom"),
        Tab(_.panel := "advanced")("Advanced"),
        Tab(_.panel := "disabled", _.disabled := true)("Disabled"),
        TabPanel(_.name := "general")("This is the general tab panel."),
        TabPanel(_.name := "custom")("This is the custom tab panel."),
        TabPanel(_.name := "advanced")("This is the advanced tab panel."),
        TabPanel(_.name := "disabled")("This is a disabled tab panel.")
      )
  })
}
  