package examples.webawesome.skeleton.example4
  
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
        styleTag("""
          .skeleton-avatars wa-skeleton {
            display: inline-flex;
            width: 3rem;
            height: 3rem;
            margin-right: 0.5rem;
          }
      
          .skeleton-avatars wa-skeleton:nth-child(1)::part(indicator) {
            border-radius: 0;
          }
      
          .skeleton-avatars wa-skeleton:nth-child(2)::part(indicator) {
            border-radius: var(--wa-border-radius-m);
          }
        """),
        div(
          cls := "skeleton-avatars",
          Skeleton()(),
          Skeleton()(),
          Skeleton()()
        )
      )
  })
}
  