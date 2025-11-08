package examples.webawesome.copyButton.h4bdc74f7f36a
  
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
        CopyButton(
          _.value := "I'm so stylish",
          _.slots.copyIcon(Icon(_.name := "clipboard")()),
          _.slots.successIcon(Icon(_.name := "thumbs-up")()),
          _.slots.errorIcon(Icon(_.name := "thumbs-down")())
        )(
          className := "custom-styles"
        ),
        styleTag("""
          .custom-styles,
          .custom-styles::part(success-icon),
          .custom-styles::part(error-icon) {
            color: white;
          }
      
          .custom-styles::part(button) {
            background-color: #ff1493;
            border: solid 2px #ff7ac1;
            border-right-color: #ad005c;
            border-bottom-color: #ad005c;
            border-radius: 6px;
            transition: all var(--wa-transition-slow) var(--wa-transition-easing);
          }
      
          .custom-styles::part(button):hover {
            transform: scale(1.05);
          }
      
          .custom-styles::part(button):active {
            transform: translateY(1px);
          }
      
          .custom-styles::part(button):focus-visible {
            outline: dashed 2px deeppink;
            outline-offset: 4px;
          }
        """)
      )
  })
}
  