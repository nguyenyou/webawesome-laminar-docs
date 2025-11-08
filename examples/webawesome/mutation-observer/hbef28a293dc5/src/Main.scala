package examples.webawesome.`mutation-observer`.hbef28a293dc5
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

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
  