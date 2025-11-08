package examples.webawesome.icon.hd1b6346a0ad4
  
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
            color := "#4a90e2",
            tw.flex.gap2.mb2,
            Icon(_.name := "exclamation-triangle")(),
            Icon(_.name := "archive")(),
            Icon(_.name := "battery-three-quarters")(),
            Icon(_.name := "bell")()
          ),
          div(
            color := "#9013fe",
            tw.flex.gap2.mb2,
            Icon(_.name := "clock")(),
            Icon(_.name := "cloud")(),
            Icon(_.name := "download")(),
            Icon(_.name := "file")()
          ),
          div(
            color := "#417505",
            tw.flex.gap2.mb2,
            Icon(_.name := "flag")(),
            Icon(_.name := "heart")(),
            Icon(_.name := "image")(),
            Icon(_.name := "bolt-lightning")()
          ),
          div(
            color := "#f5a623",
            tw.flex.gap2,
            Icon(_.name := "microphone")(),
            Icon(_.name := "search")(),
            Icon(_.name := "star")(),
            Icon(_.name := "trash")()
          ),
        )
      )
  })
}
  