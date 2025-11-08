package examples.laminar.button.example1
  
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import io.github.nguyenyou.webawesome.laminar.*

  @main def app = {
    val container = dom.document.querySelector("#root")
    render(container, {
      div(
        color := "blue",
        "Hohohoho"
      )
    })
  }
  