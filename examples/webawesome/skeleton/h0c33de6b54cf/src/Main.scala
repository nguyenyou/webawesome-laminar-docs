package examples.webawesome.skeleton.h0c33de6b54cf
  
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
        cls := "skeleton-paragraphs",
        styleTag("""
          .skeleton-paragraphs wa-skeleton {
            margin-bottom: 1rem;
          }
      
          .skeleton-paragraphs wa-skeleton:nth-child(2) {
            width: 95%;
          }
      
          .skeleton-paragraphs wa-skeleton:nth-child(4) {
            width: 90%;
          }
      
          .skeleton-paragraphs wa-skeleton:last-child {
            width: 50%;
          }
        """),
        Skeleton()(),
        Skeleton()(),
        Skeleton()(),
        Skeleton()(),
        Skeleton()()
      )
  })
}
  