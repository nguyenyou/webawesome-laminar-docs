package examples.webawesome.dropdown.example11
  
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
          Button(_.withCaret := true)("Export")
        )
      )(
        DropdownItem(
          _.slots.submenu(DropdownItem(_.value := "pdf")("PDF")),
          _.slots.submenu(DropdownItem(_.value := "docx")("Word Document"))
        )("Documents"),
        DropdownItem(
          _.slots.submenu(
            DropdownItem(
              _.slots.submenu(DropdownItem(_.value := "xlsx")("Excel (.xlsx)")),
              _.slots.submenu(DropdownItem(_.value := "xls")("Excel 97-2003 (.xls)")),
              _.slots.submenu(DropdownItem(_.value := "csv")("CSV (.csv)"))
            )("Excel Formats")
          ),
          _.slots.submenu(
            DropdownItem(
              _.slots.submenu(DropdownItem(_.value := "ods")("OpenDocument (.ods)")),
              _.slots.submenu(DropdownItem(_.value := "tsv")("Tab-separated (.tsv)")),
              _.slots.submenu(DropdownItem(_.value := "json")("JSON (.json)"))
            )("Other Formats")
          ),
          _.slots.submenu(DropdownItem(_.value := "numbers")("Apple Numbers"))
        )("Spreadsheets"),
        Divider()(),
        DropdownItem(
          _.slots.submenu(DropdownItem(_.`type`.checkbox, _.value := "compress")("Compress files")),
          _.slots.submenu(
            DropdownItem(_.`type`.checkbox, _.checked := true, _.value := "metadata")("Include metadata")
          ),
          _.slots.submenu(DropdownItem(_.`type`.checkbox, _.value := "password")("Password protect"))
        )("Options")
      )
  })
}
  