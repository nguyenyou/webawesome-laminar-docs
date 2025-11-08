package examples.webawesome.input.example13
  
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
        cls := "label-on-left",
        styleTag("""
          .label-on-left {
            display: grid;
            grid-template-columns: auto 1fr;
            gap: var(--wa-space-l);
            align-items: center;
          }
          
          .label-on-left wa-input,
          .label-on-left wa-textarea {
            grid-column: 1 / -1;
            grid-row-end: span 2;
            display: grid;
            grid-template-columns: subgrid;
            gap: 0 var(--wa-space-l);
            align-items: center;
          }
          
          .label-on-left ::part(label) {
            text-align: right;
          }
          
          .label-on-left ::part(hint) {
            grid-column: 2;
          }
        """),
        Input(
          _.label := "Name",
          _.hint  := "Enter your name"
        )(),
        Input(
          _.label := "Email",
          _.typ.email,
          _.hint := "Enter your email"
        )(),
        Textarea(
          _.label := "Bio",
          _.hint  := "Tell us something about yourself"
        )()
      )
  })
}
  