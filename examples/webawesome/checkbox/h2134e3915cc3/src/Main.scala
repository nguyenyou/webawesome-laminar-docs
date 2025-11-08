package examples.webawesome.checkbox.h2134e3915cc3
  
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
          Checkbox(_.size.small)("Small"),
          Checkbox(_.size.medium)("Medium"),
          Checkbox(_.size.large)("Large"),
        )
      )
  })
}
  