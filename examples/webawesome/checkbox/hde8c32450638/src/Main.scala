package examples.webawesome.checkbox.hde8c32450638
  
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
          form(
            tw.flex.flexCol.itemsStart.gap4,
            Checkbox(
              _.name     := "agree",
              _.required := true
            )("Check me"),
            Button(
              _.`type`.submit,
              _.variant.brand
            )("Submit")
          ),
        )
      )
  })
}
  