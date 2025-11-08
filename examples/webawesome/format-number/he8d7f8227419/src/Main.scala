package examples.webawesome.`format-number`.he8d7f8227419
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      val valueVar = Var("1000")
      
      div(
        cls := "format-number-overview",
        p(FormatNumber(_.value <-- valueVar.signal.map(_.toDouble))()),
        Input(
          _.`type`.number,
          _.value <-- valueVar,
          _.label := "Number to Format"
        )(
          onInput.mapToValue --> valueVar,
          maxWidth.px(180)
        )
      )
  })
}
  