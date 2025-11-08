package examples.webawesome.mutationObserver.example1
  
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
      val variants  = List[ThemeVariant]("brand", "success", "neutral", "warning", "danger")
      val clicksVar = Var(0)
      
      div(
        MutationObserver(
          _.attr := "variant",
          _.onMutation.map { event =>
            println(event.detail)
          } --> Observer.empty
        )(
          Button(
            _.variant <-- clicksVar.signal.map(clicks => variants(clicks % variants.length))
          )(
            onClick --> Observer[dom.MouseEvent] { _ =>
              clicksVar.update(_ + 1)
            },
            "Click to mutate"
          )
        ),
        br(),
        "👆 Click the button and watch the console"
      )
  })
}
  