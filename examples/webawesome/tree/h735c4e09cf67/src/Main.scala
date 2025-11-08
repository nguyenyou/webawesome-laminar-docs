package examples.webawesome.tree.h735c4e09cf67
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
      Tree(_.selection.multiple)(
        TreeItem()(
          "Parent Node",
          TreeItem(_.selected := true)("Child Node 1"),
          TreeItem()(
            "Child Node 2",
            TreeItem()("Child Node 2 - 1"),
            TreeItem()("Child Node 2 - 2")
          )
        )
      )
  })
}
  