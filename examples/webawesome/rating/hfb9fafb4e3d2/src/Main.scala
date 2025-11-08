package examples.webawesome.rating.hfb9fafb4e3d2
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      ExampleGroups(
        Examples(
          val hoverText = Var(""),
          val terms     = List("No rating", "Terrible", "Bad", "OK", "Good", "Excellent"),
        ),
        Examples(
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
              tw.relative.textCenter.py1.px2,
              tw.hidden <-- hoverText.signal.map(_.isEmpty),
              top.px(-4),
              left.px(8),
              borderRadius    := "var(--wa-border-radius-m)",
              backgroundColor := "var(--wa-color-neutral-fill-loud)",
              color           := "var(--wa-color-neutral-on-loud)",
              text <-- hoverText.signal
            )
          ),
        )
      )
  })
}
  