package examples.webawesome.mutationObserver.h30b6d5ddcf56
  
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
      val buttonCountVar = Var(0)
      
      div(
        MutationObserver(
          _.childList := true,
          _.onMutation.map { event =>
            println(event.detail)
          } --> Observer.empty
        )(
          div(
            cls("flex flex-wrap gap-2"),
            Button(_.variant := "brand")(
              onClick --> Observer[dom.MouseEvent] { _ =>
                buttonCountVar.update(_ + 1)
              },
              "Add button"
            ),
            children <-- buttonCountVar.signal.map { count =>
              (1 to count).map { i =>
                Button()(
                  onClick --> Observer[dom.MouseEvent] { event =>
                    event.target.asInstanceOf[dom.Element].remove()
                    event.stopPropagation()
                  },
                  i
                )
              }
            }
          )
        ),
        "👆 Add and remove buttons and watch the console"
      )
  })
}
  