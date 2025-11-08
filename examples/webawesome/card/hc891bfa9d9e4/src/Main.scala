package examples.webawesome.card.hc891bfa9d9e4
  
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
          Card(
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
                alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
              )
            )
          )(
            maxWidth.px(200),
            "Outlined (default)"
          ),
          Card(
            _.appearance := "filled outlined",
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
                alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
              )
            )
          )(
            maxWidth.px(200),
            "Outlined filled"
          ),
          Card(
            _.appearance := "plain",
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
                alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
              )
            )
          )(
            maxWidth.px(200),
            "Plain"
          ),
          Card(
            _.appearance := "filled",
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
                alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
              )
            )
          )(
            maxWidth.px(200),
            "Filled"
          ),
          Card(
            _.appearance := "accent",
            _.slots.media(
              img(
                src := "https://images.unsplash.com/photo-1559209172-0ff8f6d49ff7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=80",
                alt := "A kitten sits patiently between a terracotta pot and decorative grasses."
              )
            )
          )(
            maxWidth.px(200),
            "Accent"
          ),
        )
      )
  })
}
  