package examples.webawesome.progressRing.h243711e36e4c
  
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
      val progressValue = Var(50.0)
      
      div(
        cls("flex flex-col gap-4"),
        ProgressRing(
          _.value <-- progressValue.signal
        )(
          child.text <-- progressValue.signal.map(v => s"${v.toInt}%")
        ),
        div(
          cls("flex gap-2"),
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
      )
  })
}
  