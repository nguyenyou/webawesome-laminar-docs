package examples.webawesome.button.h7c93a450ba0c
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Examples(
        Button(
          _.size.small,
          _.slots.start(Icon(_.name := "gear", _.label := "Settings")())
        )("Settings"),
        Button(
          _.size.small,
          _.slots.end(Icon(_.name := "undo", _.label := "Refresh")())
        )("Refresh"),
        Button(
          _.size.small,
          _.slots.start(Icon(_.name := "link", _.label := "Link")()),
          _.slots.end(
            Icon(
              _.name  := "arrow-up-right-from-square",
              _.label := "Open in new tab"
            )()
          )
        )("Open"),
        Button(
          _.size.medium,
          _.slots.start(Icon(_.name := "gear", _.label := "Settings")())
        )("Settings"),
        Button(
          _.size.medium,
          _.slots.end(Icon(_.name := "undo", _.label := "Refresh")())
        )("Refresh"),
        Button(
          _.size.medium,
          _.slots.start(Icon(_.name := "link", _.label := "Link")()),
          _.slots.end(
            Icon(
              _.name  := "arrow-up-right-from-square",
              _.label := "Open in new tab"
            )()
          )
        )("Open"),
        Button(
          _.size.large,
          _.slots.start(Icon(_.name := "gear", _.label := "Settings")())
        )("Settings"),
        Button(
          _.size.large,
          _.slots.end(Icon(_.name := "undo", _.label := "Refresh")())
        )("Refresh"),
        Button(
          _.size.large,
          _.slots.start(Icon(_.name := "link", _.label := "Link")()),
          _.slots.end(
            Icon(
              _.name  := "arrow-up-right-from-square",
              _.label := "Open in new tab"
            )()
          )
        )("Open"),
      )
  })
}
  