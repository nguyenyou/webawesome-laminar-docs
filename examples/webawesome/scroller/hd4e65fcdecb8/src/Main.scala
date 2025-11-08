package examples.webawesome.scroller.hd4e65fcdecb8
  
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
          Scroller(
            _.withoutShadow := true
          )(
            div(
              width.px := 1500,
              p(
                "Gaming consoles are like time machines for nerds, zapping us from pixelated 2D adventures to jaw-dropping cinematic worlds. Back in the '90s, the Super Nintendo was the cool kid on the block, using a 16-bit chip to pull off tricks like Mode 7, which made Mario Kart's tracks feel like they were zooming right at you. It was like wizardry for a kid with a chunky controller, turning flat sprites into pseudo-3D races that had us yelling at our TVs when we got hit by a red shell."
              ),
              p(
                "Fast-forward to today, and consoles like the PlayStation 5 and Xbox Series X are basically supercomputers in sleek boxes. They're packing enough power to make games look like Hollywood blockbusters, with lighting so real you can practically feel the sun glare in Spider-Man: Miles Morales. These machines can handle massive open worlds, like the sprawling lands of Elden Ring, without breaking a sweat, letting you swing swords or race cars while your living room feels like a sci-fi movie set. It's a far cry from the SNES days, but the vibe's the same: pure, controller-gripping fun."
              ),
              p(
                "What makes consoles the heart of gaming culture is how they bring everyone together, from casual players to hardcore speedrunners. Whether it's your uncle fumbling through Super Mario World in '92 or your best friend screaming during a late-night Call of Duty match, consoles are the ultimate couch co-op machines. Modern systems even let you stream your clutch Fortnite wins to the world or jump into crossplay with PC pals. From the GameCube's quirky handle to the Switch's grab-and-go joy-cons, every console's got its own personality, making every era of gaming feel like a legendary chapter in a never-ending quest."
              )
            )
          ),
        )
      )
  })
}
  