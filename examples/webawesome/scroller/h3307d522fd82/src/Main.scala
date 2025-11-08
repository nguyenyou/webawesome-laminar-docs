package examples.webawesome.scroller.h3307d522fd82
  
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
          Scroller()(
            div(
              width.px    := 1200,
              padding.rem := 1,
              h3("Superhero Team Roles Guide"),
              div(
                cls       := "wa-grid",
                styleAttr := "--wa-grid-columns: 4; --wa-grid-gap: var(--wa-spacing-l);",
                div(
                  h4("Team Leaders"),
                  p(
                    "Charismatic captains like Captain America or Cyclops are the heart of any superteam, rallying everyone with epic speeches and killer strategies. They're the ones calling the shots in a cosmic showdown, keeping the squad focused when Thanos or Magneto crashes the party."
                  )
                ),
                div(
                  h4("Heavy Hitters"),
                  p(
                    "Powerhouses like Thor or Hulk bring the boom, smashing through villain lairs or alien armadas. Their job is to land the big punches, but they gotta pace themselves to avoid stealing the spotlight from sneakier teammates."
                  )
                ),
                div(
                  h4("Tech Geniuses"),
                  p(
                    "Brainiacs like Iron Man or Mr. Fantastic keep the team one step ahead with gadgets and gizmos. They're crafting quinjets or hacking evil AI, always ready with a snarky quip while saving the day from a computer terminal."
                  )
                ),
                div(
                  h4("Stealth Specialists"),
                  p(
                    "Ninja-like heroes like Black Widow or Nightcrawler slip through the shadows, gathering intel or pulling off surprise attacks. They're the glue that makes risky plans work, coordinating with the team to flip a losing fight into a win."
                  )
                )
              )
            )
          ),
        )
      )
  })
}
  