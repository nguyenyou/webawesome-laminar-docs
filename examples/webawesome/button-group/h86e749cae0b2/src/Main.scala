package examples.webawesome.`button-group`.h86e749cae0b2
  
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
          div(
            ButtonGroup(_.label := "Alignment")(
              Button(_.id := "button-left")("Left"),
              Button(_.id := "button-center")("Center"),
              Button(_.id := "button-right")("Right")
            ),
            Tooltip(_.forId := "button-left")("I'm on the left"),
            Tooltip(_.forId := "button-center")("I'm in the middle"),
            Tooltip(_.forId := "button-right")("I'm on the right")
          ),
        )
      )
  })
}
  