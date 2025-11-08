package doc

import com.raquo.laminar.api.L.*

def Examples(children: HtmlElement*) = {
  div(
    display.flex,
    gap.px(8),
    children
  )
}