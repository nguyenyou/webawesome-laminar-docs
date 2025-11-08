package examples.webawesome.skeleton.h0ed55b27554b
  
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
          ),
        )
      )
  })
}
  