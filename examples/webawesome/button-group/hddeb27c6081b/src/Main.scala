package examples.webawesome.`button-group`.hddeb27c6081b
  
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
            styleTag(
              """
              .button-group-toolbar wa-button-group:not(:last-of-type) {
                margin-right: var(--wa-space-xs);
              }
              """
            ),
            div(
              className := "button-group-toolbar",
              ButtonGroup(_.label := "History")(
                Button(_.id := "undo-button")(
                  Icon(_.name := "undo", _.variant := "solid", _.label := "Undo")()
                ),
                Button(_.id := "redo-button")(
                  Icon(_.name := "redo", _.variant := "solid", _.label := "Redo")()
                )
              ),
              ButtonGroup(_.label := "Formatting")(
                Button(_.id := "button-bold")(
                  Icon(_.name := "bold", _.variant := "solid", _.label := "Bold")()
                ),
                Button(_.id := "button-italic")(
                  Icon(_.name := "italic", _.variant := "solid", _.label := "Italic")()
                ),
                Button(_.id := "button-underline")(
                  Icon(_.name := "underline", _.variant := "solid", _.label := "Underline")()
                )
              ),
              ButtonGroup(_.label := "Alignment")(
                Button(_.id := "button-align-left")(
                  Icon(_.name := "align-left", _.variant := "solid", _.label := "Align Left")()
                ),
                Button(_.id := "button-align-center")(
                  Icon(_.name := "align-center", _.variant := "solid", _.label := "Align Center")()
                ),
                Button(_.id := "button-align-right")(
                  Icon(_.name := "align-right", _.variant := "solid", _.label := "Align Right")()
                )
              )
            ),
            Tooltip(_.forId := "undo-button")("Undo"),
            Tooltip(_.forId := "redo-button")("Redo"),
            Tooltip(_.forId := "button-bold")("Bold"),
            Tooltip(_.forId := "button-italic")("Italic"),
            Tooltip(_.forId := "button-underline")("Underline"),
            Tooltip(_.forId := "button-align-left")("Align Left"),
            Tooltip(_.forId := "button-align-center")("Align Center"),
            Tooltip(_.forId := "button-align-right")("Align Right")
          ),
        )
      )
  })
}
  