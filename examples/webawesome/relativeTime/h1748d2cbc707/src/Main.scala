package examples.webawesome.relativeTime.h1748d2cbc707
  
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
      RelativeTime(
        _.sync := true,
        _.date := {
          // Set the date to 1 minute ago
          val oneMinuteAgo = new js.Date(js.Date.now() - 60000)
          oneMinuteAgo.toISOString()
        }
      )()
  })
}
  