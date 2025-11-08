package examples.webawesome.`copy-button`.h59fa33c786e6
  
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
            // Copies the span's textContent
            span(idAttr := "my-phone", "+1 (234) 456-7890"),
            " ",
            CopyButton(_.from := "my-phone")(),
            br(),
            br(),
          
            // Copies the input's "value" property
            span(
              tw.flex.itemsCenter.gap1,
              Input(
                _.id := "my-input",
                _.`type`.text,
                _.value := "User input"
              )(
                styleAttr := "display: inline-block; max-width: 300px;"
              ),
              CopyButton(_.from := "my-input.value")()
            ),
            br(),
          
            // Copies the link's "href" attribute
            a(idAttr := "my-link", href := "https://webawesome.com/", "Web Awesome Website"),
            " ",
            CopyButton(_.from := "my-link[href]")()
          ),
        )
      )
  })
}
  