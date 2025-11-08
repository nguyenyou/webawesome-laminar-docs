package examples.webawesome.skeleton.h6a692d760901
  
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
            cls := "skeleton-shapes",
            styleTag("""
              .skeleton-shapes wa-skeleton {
                display: inline-flex;
                width: 50px;
                height: 50px;
              }
          
              .skeleton-shapes .square::part(indicator) {
                border-radius: var(--wa-border-radius-m);
              }
          
              .skeleton-shapes .circle::part(indicator) {
                border-radius: var(--wa-border-radius-circle);
              }
          
              .skeleton-shapes .triangle::part(indicator) {
                border-radius: 0;
                clip-path: polygon(50% 0, 0 100%, 100% 100%);
              }
          
              .skeleton-shapes .cross::part(indicator) {
                border-radius: 0;
                clip-path: polygon(
                  20% 0%,
                  0% 20%,
                  30% 50%,
                  0% 80%,
                  20% 100%,
                  50% 70%,
                  80% 100%,
                  100% 80%,
                  70% 50%,
                  100% 20%,
                  80% 0%,
                  50% 30%
                );
              }
          
              .skeleton-shapes .comment::part(indicator) {
                border-radius: 0;
                clip-path: polygon(0% 0%, 100% 0%, 100% 75%, 75% 75%, 75% 100%, 50% 75%, 0% 75%);
              }
          
              .skeleton-shapes wa-skeleton:not(:last-child) {
                margin-right: 0.5rem;
              }
            """),
            Skeleton()(cls := "square"),
            Skeleton()(cls := "circle"),
            Skeleton()(cls := "triangle"),
            Skeleton()(cls := "cross"),
            Skeleton()(cls := "comment")
          ),
        )
      )
  })
}
  