package examples.webawesome.dropdown.hf88cd08678f5
  
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
          ),
        )
      )
  })
}
  