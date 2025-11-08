package examples.webawesome.button-group.he7ffb5f22118
  
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
          ButtonGroup(_.orientation.vertical, _.label := "Options")(
            maxWidth.px(120),
            Button(
              _.slots.start(Icon(_.name := "plus")())
            )("New"),
            Button(
              _.slots.start(Icon(_.name := "folder-open")())
            )("Open"),
            Button(
              _.slots.start(Icon(_.name := "save")())
            )("Save"),
            Button(
              _.slots.start(Icon(_.name := "print")())
            )("Print")
          ),
        )
      )
  })
}
  