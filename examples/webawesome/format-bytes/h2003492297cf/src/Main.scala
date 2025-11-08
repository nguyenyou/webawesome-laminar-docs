package examples.webawesome.`format-bytes`.h2003492297cf
  
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
        p("The file is ", FormatBytes(_.value <-- valueVar.signal.map(_.toDouble))(), " in size."),
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
  