package examples.webawesome.skeleton.example2
  
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
      Skeleton(_.effect.none)()
      Skeleton(_.effect.sheen)()
      Skeleton(_.effect.pulse)()
  })
}
  