package examples.webawesome.tree.h54849f007ad9
  
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
      Tree()(
        TreeItem(_.expanded := true)(
          Icon(_.name := "folder", _.variant := "regular")(),
          "Documents",
          TreeItem()(
            Icon(_.name := "folder", _.variant := "regular")(),
            "Photos",
            TreeItem()(
              Icon(_.name := "image", _.variant := "regular")(),
              "birds.jpg"
            ),
            TreeItem()(
              Icon(_.name := "image", _.variant := "regular")(),
              "kitten.jpg"
            ),
            TreeItem()(
              Icon(_.name := "image", _.variant := "regular")(),
              "puppy.jpg"
            )
          ),
          TreeItem()(
            Icon(_.name := "folder", _.variant := "regular")(),
            "Writing",
            TreeItem()(
              Icon(_.name := "file", _.variant := "regular")(),
              "draft.txt"
            ),
            TreeItem()(
              Icon(_.name := "file-pdf", _.variant := "regular")(),
              "final.pdf"
            ),
            TreeItem()(
              Icon(_.name := "file-lines", _.variant := "regular")(),
              "sales.xls"
            )
          )
        )
      )
  })
}
  