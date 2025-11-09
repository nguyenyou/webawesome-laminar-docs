package examples.webawesome.icon

@main
def app = {
  example1()
  example2()
  example3()
  example4()
  example5()
  example6()
  example7()
}

def example1() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example1")
  if (container != null) {
    render(container, {
      Icon(
        _.name := "star"
      )()
    })
  }
}

def example2() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example2")
  if (container != null) {
    render(container, {
      Icon(
        _.name := "eyedropper"
      )()
      Icon(
        _.name := "grip-vertical"
      )()
      Icon(
        _.name := "play"
      )()
      Icon(
        _.name := "star"
      )()
      Icon(
        _.name := "user"
      )()
    })
  }
}

def example3() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example3")
  if (container != null) {
    render(container, {
      div(
        color := "#4a90e2",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "exclamation-triangle")(),
        Icon(_.name := "archive")(),
        Icon(_.name := "battery-three-quarters")(),
        Icon(_.name := "bell")()
      )
      div(
        color := "#9013fe",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "clock")(),
        Icon(_.name := "cloud")(),
        Icon(_.name := "download")(),
        Icon(_.name := "file")()
      )
      div(
        color := "#417505",
        cls("flex gap-2 mb-2"),
        Icon(_.name := "flag")(),
        Icon(_.name := "heart")(),
        Icon(_.name := "image")(),
        Icon(_.name := "bolt-lightning")()
      )
      div(
        color := "#f5a623",
        cls("flex gap-2"),
        Icon(_.name := "microphone")(),
        Icon(_.name := "search")(),
        Icon(_.name := "star")(),
        Icon(_.name := "trash")()
      )
    })
  }
}

def example4() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example4")
  if (container != null) {
    render(container, {
      div(
        fontSize := "32px",
        cls("flex gap-2"),
        Icon(_.name := "bell")(),
        Icon(_.name := "heart")(),
        Icon(_.name := "image")(),
        Icon(_.name := "microphone")(),
        Icon(_.name := "search")(),
        Icon(_.name := "star")()
      )
    })
  }
}

def example5() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example5")
  if (container != null) {
    render(container, {
      Icon(
        _.fixedWidth := "true",
        _.name       := "cloud"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "user"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "truck"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "file"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "skating"
      )()
      Icon(
        _.fixedWidth := "true",
        _.name       := "snowplow"
      )()
    })
  }
}

def example6() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example6")
  if (container != null) {
    render(container, {
      Icon(
        _.name  := "star",
        _.label := "Add to favorites"
      )()
    })
  }
}

def example7() = {
  import org.scalajs.dom
  import com.raquo.laminar.api.L.*
  import doc.*
  import doc.facades.*
  import org.scalajs.dom.window
  import io.github.nguyenyou.webawesome.laminar.*
  import io.github.nguyenyou.webawesome.laminar.SharedTypes.*
  import io.github.nguyenyou.webawesome.laminar.CommonKeys.TreeSelection
  import scala.scalajs.js

  val container = dom.document.querySelector("#example7")
  if (container != null) {
    render(container, {
      Icon(
        _.src   := "https://shoelace.style/assets/images/shoe.svg",
        _.style := "font-size: 4rem;"
      )()
    })
  }
}
