package examples.webawesome.avatar.h52ac21b71da8
  
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
          div(
            cls("avatar-group"),
            styleTag("""
              .avatar-group wa-avatar:not(:first-of-type) {
                margin-left: calc(-1 * var(--wa-space-m));
              }
          
              .avatar-group wa-avatar {
                border: solid 2px var(--wa-color-surface-default);
              }
            """),
            Avatar(
              _.image := "https://images.unsplash.com/photo-1490150028299-bf57d78394e0?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=256&h=256&q=80&crop=right",
              _.label := "Avatar 1 of 4"
            )(),
            Avatar(
              _.image := "https://images.unsplash.com/photo-1503454537195-1dcabb73ffb9?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=256&h=256&crop=left&q=80",
              _.label := "Avatar 2 of 4"
            )(),
            Avatar(
              _.image := "https://images.unsplash.com/photo-1456439663599-95b042d50252?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=256&h=256&crop=left&q=80",
              _.label := "Avatar 3 of 4"
            )(),
            Avatar(
              _.image := "https://images.unsplash.com/flagged/photo-1554078875-e37cb8b0e27d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=256&h=256&crop=top&q=80",
              _.label := "Avatar 4 of 4"
            )()
          ),
        )
      )
  })
}
  