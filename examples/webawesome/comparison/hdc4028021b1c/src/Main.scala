package examples.webawesome.comparison.hdc4028021b1c
  
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
          Comparison(
            _.position := 25,
            _.slots.before(
              img(
                src := "https://images.unsplash.com/photo-1520903074185-8eca362b3dce?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1200&q=80",
                alt := "A person sitting on bricks wearing untied boots."
              )
            ),
            _.slots.after(
              img(
                src := "https://images.unsplash.com/photo-1520640023173-50a135e35804?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80",
                alt := "A person sitting on a yellow curb tying shoelaces on a boot."
              )
            )
          )(),
        )
      )
  })
}
  