package examples.webawesome.popover.hd848ea79d649
  
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
        Popover(
          _.forId := "popover__overview"
        )(
          div(
            cls("flex flex-col gap-4"),
      
            p("This popover contains interactive content that users can engage with directly."),
            Button(
              _.variant.brand,
              _.size.small
            )("Take Action")
          )
        ),
        Button(
          _.id := "popover__overview"
        )("Show popover")
      )
  })
}
  