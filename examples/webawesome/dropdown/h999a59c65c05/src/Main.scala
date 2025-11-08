package examples.webawesome.dropdown.h999a59c65c05
  
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
          Dropdown(
            _.slots.trigger(
              Button(
                _.withCaret := true,
                _.slots.end(
                  Icon(_.name := "chevron-right")()
                )
              )("File formats")
            )
          )(
            DropdownItem(_.value := "pdf")("PDF Document"),
            DropdownItem(_.value := "docx")("Word Document"),
            DropdownItem(_.value := "xlsx")("Excel Spreadsheet"),
            DropdownItem(_.value := "pptx")("PowerPoint Presentation"),
            DropdownItem(_.value := "txt")("Plain Text"),
            DropdownItem(_.value := "json")("JSON File")
          ),
        )
      )
  })
}
  