package examples.webawesome.details.hd48b06082b8d
  
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
          Details(
            _.summary := "تبديلني"
          )(
            lang := "ar",
            dir  := "rtl",
            "استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن"
          ),
        )
      )
  })
}
  