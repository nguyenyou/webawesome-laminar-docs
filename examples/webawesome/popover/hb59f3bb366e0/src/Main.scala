package examples.webawesome.popover.hb59f3bb366e0
  
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
            Button(
              _.id := "popover__max-width"
            )("Toggle me"),
            Popover(
              _.forId := "popover__max-width",
              _.style := "--max-width: 160px;"
            )(
              "Popovers will usually grow to be much wider, but this one has a custom max width that forces text to wrap."
            )
          ),
        )
      )
  })
}
  