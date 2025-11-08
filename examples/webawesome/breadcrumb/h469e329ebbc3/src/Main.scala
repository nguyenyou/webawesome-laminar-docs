package examples.webawesome.breadcrumb.h469e329ebbc3
  
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
      Breadcrumb()(
        BreadcrumbItem(_.href := "https://example.com/home")("Homepage"),
        BreadcrumbItem(_.href := "https://example.com/home/services")("Our Services"),
        BreadcrumbItem(_.href := "https://example.com/home/services/digital")("Digital Media"),
        BreadcrumbItem(_.href := "https://example.com/home/services/digital/web-design")("Web Design")
      )
  })
}
  