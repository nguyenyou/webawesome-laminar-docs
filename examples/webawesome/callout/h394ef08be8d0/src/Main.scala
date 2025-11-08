package examples.webawesome.callout.h394ef08be8d0
  
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
          Callout(
            _.size.large,
            _.slots.icon(Icon(_.name := "circle-info")())
          )(
            "This is meant to be very emphasized."
          ),
          Callout(
            _.size.medium,
            _.slots.icon(Icon(_.name := "circle-info")())
          )(
            "Normal-sized callout."
          ),
          Callout(
            _.size.small,
            _.slots.icon(Icon(_.name := "circle-info")())
          )(
            "Just a small tip!"
          ),
        )
      )
  })
}
  