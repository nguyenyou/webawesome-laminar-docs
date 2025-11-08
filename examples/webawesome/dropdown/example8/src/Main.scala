package examples.webawesome.dropdown.example8
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import doc.facades.*
import org.scalajs.dom.window
import io.github.nguyenyou.webawesome.laminar.*
import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection

import scala.scalajs.js

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
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
      )
  })
}
  