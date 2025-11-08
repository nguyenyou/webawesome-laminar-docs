package examples.webawesome.animation.h57ff198f9f00
  
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
         .animation-overview .box {
            display: inline-block;
            width: 100px;
            height: 100px;
            background-color: var(--wa-color-brand-fill-loud);
            margin: 1.5rem;
          }
        """),
        cls("animation-overview"),
        Animation(
          _.name     := "bounce",
          _.duration := 2000,
          _.play     := true
        )(
          div(cls("box"))
        ),
        Animation(
          _.name     := "jello",
          _.duration := 2000,
          _.play     := true
        )(
          div(cls("box"))
        ),
        Animation(
          _.name     := "heartBeat",
          _.duration := 2000,
          _.play     := true
        )(
          div(cls("box"))
        ),
        Animation(
          _.name     := "flip",
          _.duration := 2000,
          _.play     := true
        )(
          div(cls("box"))
        )
      )
  })
}
  