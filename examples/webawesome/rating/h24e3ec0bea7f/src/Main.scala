package examples.webawesome.rating.h24e3ec0bea7f
  
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
      val hoverText = Var("")
      val terms     = List("No rating", "Terrible", "Bad", "OK", "Good", "Excellent")
      
      div(
        Rating(
          _.label := "Rating",
          _.onHover.map { event =>
            val value = event.detail.value
            hoverText.set(terms.lift(value).getOrElse(""))
            if (event.detail.phase == "end") {
              hoverText.set("")
            }
          } --> Observer.empty
        )(),
        span(
          cls("relative text-center py-1 px-2"),
          cls("hidden") <-- hoverText.signal.map(_.isEmpty),
          top.px(-4),
          left.px(8),
          borderRadius    := "var(--wa-border-radius-m)",
          backgroundColor := "var(--wa-color-neutral-fill-loud)",
          color           := "var(--wa-color-neutral-on-loud)",
          text <-- hoverText.signal
        )
      )
  })
}
  