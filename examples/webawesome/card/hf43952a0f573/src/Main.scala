package examples.webawesome.card.hf43952a0f573
  
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
          Card()(
            maxWidth.px(300),
            "This is just a basic card. No media, no header, and no footer. Just your content."
          ),
        )
      )
  })
}
  