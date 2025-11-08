package examples.webawesome.spinner.hd546d5b1a642
  
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
          Spinner()(),
          Spinner(
            _.style := "font-size: 2rem;"
          )(),
          Spinner(
            _.style := "font-size: 3rem;"
          )(),
        )
      )
  })
}
  