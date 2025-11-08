package examples.webawesome.`progress-bar`.hd9cf8b96439c
  
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
          val progressValue = Var(50.0),
        ),
        Examples(
          div(
            tw.flex.flexCol.gap4,
            ProgressBar(
              _.value <-- progressValue.signal
            )(
              child.text <-- progressValue.signal.map(v => s"${v.toInt}%")
            ),
            div(
              tw.flex.gap2,
              Button(
                _.pill := true
              )(
                onClick --> Observer[dom.MouseEvent] { _ =>
                  progressValue.update(current => math.max(0, current - 10))
                },
                Icon(_.name := "minus")()
              ),
              Button(
                _.pill := true
              )(
                onClick --> Observer[dom.MouseEvent] { _ =>
                  progressValue.update(current => math.min(100, current + 10))
                },
                Icon(_.name := "plus")()
              )
            )
          ),
        )
      )
  })
}
  