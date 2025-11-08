package examples.webawesome.skeleton.h0c79dadf4ff7
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      div(
        styleTag("""
          .skeleton-overview .skeleton-header {
            display: flex;
            align-items: center;
            margin-bottom: 1rem;
          }
      
          .skeleton-overview .skeleton-header wa-skeleton:last-child {
            flex: 0 0 auto;
            width: 30%;
          }
      
          .skeleton-overview wa-skeleton {
            margin-bottom: 1rem;
          }
      
          .skeleton-overview wa-skeleton:nth-child(1) {
            float: left;
            width: 3rem;
            height: 3rem;
            margin-right: 1rem;
            vertical-align: middle;
          }
      
          .skeleton-overview wa-skeleton:nth-child(3) {
            width: 95%;
          }
      
          .skeleton-overview wa-skeleton:nth-child(4) {
            width: 80%;
          }
        """),
        div(
          cls := "skeleton-overview",
          div(
            cls := "skeleton-header",
            Skeleton(_.effect.sheen)(),
            Skeleton(_.effect.sheen)()
          ),
          Skeleton(_.effect.sheen)(),
          Skeleton(_.effect.sheen)(),
          Skeleton(_.effect.sheen)()
        )
      )
  })
}
  