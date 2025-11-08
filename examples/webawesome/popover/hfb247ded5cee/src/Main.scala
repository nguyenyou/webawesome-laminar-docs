package examples.webawesome.popover.hfb247ded5cee
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        Popover(
          _.forId := "popover__overview"
        )(
          div(
            tw.flex.flexCol.gap4,
            p("This popover contains interactive content that users can engage with directly."),
            Button(
              _.variant.brand,
              _.size.small
            )("Take Action")
          )
        ),
        Button(
          _.id := "popover__overview"
        )("Show popover")
      )
  })
}
  