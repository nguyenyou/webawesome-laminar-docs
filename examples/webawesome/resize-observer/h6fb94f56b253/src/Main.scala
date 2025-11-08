package examples.webawesome.resize-observer.h6fb94f56b253
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        ResizeObserver(
          _.onResize.map { event =>
            dom.console.log(event.detail)
          } --> Observer.empty
        )(
          div(
            tw.flex.itemsCenter.justifyCenter.textCenter,
            border  := "2px solid var(--wa-color-surface-border)",
            padding := "4rem 2rem",
            "Resize this box and watch the console 👉"
          )
        )
      )
  })
}
  