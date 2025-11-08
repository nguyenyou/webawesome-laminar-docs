package examples.webawesome.relativeTime.h328c4b436fbc
  
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
        p("Web Awesome 2 release date 🎉"),
        RelativeTime(_.date := "2020-07-15T09:17:00-04:00")()
      )
  })
}
  