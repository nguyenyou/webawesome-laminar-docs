package examples.webawesome.tab-group.h7ee409284093
  
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
          TabGroup(_.placement.start)(
            Tab(_.panel := "general")("General"),
            Tab(_.panel := "custom")("Custom"),
            Tab(_.panel := "advanced")("Advanced"),
            Tab(_.panel := "disabled", _.disabled := true)("Disabled"),
            TabPanel(_.name := "general")("This is the general tab panel."),
            TabPanel(_.name := "custom")("This is the custom tab panel."),
            TabPanel(_.name := "advanced")("This is the advanced tab panel."),
            TabPanel(_.name := "disabled")("This is a disabled tab panel.")
          ),
        )
      )
  })
}
  