package examples.webawesome.icon.haaac009bd7b6
  
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
            fontSize := "32px",
            tw.flex.gap2,
            Icon(_.name := "bell")(),
            Icon(_.name := "heart")(),
            Icon(_.name := "image")(),
            Icon(_.name := "microphone")(),
            Icon(_.name := "search")(),
            Icon(_.name := "star")()
          ),
        )
      )
  })
}
  