package examples.webawesome.copyButton.example4
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        // Copies the span's textContent
        span(idAttr := "my-phone", "+1 (234) 456-7890"),
        " ",
        CopyButton(_.from := "my-phone")(),
        br(),
        br(),
      
        // Copies the input's "value" property
        span(
          cls("flex items-center gap-1"),
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
      )
  })
}
  