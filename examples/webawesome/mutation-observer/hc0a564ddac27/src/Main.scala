package examples.webawesome.`mutation-observer`.hc0a564ddac27
  
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
          val buttonCountVar = Var(0),
        ),
        Examples(
          div(
            MutationObserver(
              _.childList := true,
              _.onMutation.map { event =>
                println(event.detail)
              } --> Observer.empty
            )(
              div(
                tw.flex.flexWrap.gap2,
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
          ),
        )
      )
  })
}
  