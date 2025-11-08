package examples.webawesome.comparison.h68bda4932edc
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Comparison(
        _.slots.before(
          img(
            src := "https://images.unsplash.com/photo-1517331156700-3c241d2b4d83?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80&sat=-100&bri=-5",
            alt := "Grayscale version of kittens in a basket looking around."
          )
        ),
        _.slots.after(
          img(
            src := "https://images.unsplash.com/photo-1517331156700-3c241d2b4d83?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=80",
            alt := "Color version of kittens in a basket looking around."
          )
        )
      )()
  })
}
  