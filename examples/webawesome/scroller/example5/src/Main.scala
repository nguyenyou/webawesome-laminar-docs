package examples.webawesome.scroller.example5
  
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
      Scroller(
        _.withoutScrollbar := true
      )(
        div(
          width.px := 1500,
          p(
            "Dungeons & Dragons 5e is the blockbuster superhero flick of tabletop RPGs, turning every session into an epic tavern brawl or dragon-slaying saga. Unlike the old 3.5e days, where you'd stack +30 bonuses like a mathlete on a mission, 5e keeps things chill with skill checks capping around +11—like a +5 from your slick Charisma and +6 from being a pro at persuasion. This means even a squad of scrappy kobolds can give your level 10 barbarian a bad day if you roll poorly. It's like the game's saying, \"Sure, you're a hero, but don't get cocky!\""
          ),
          p(
            "The advantage and disadvantage system is 5e's secret sauce, making every dice roll feel like a movie cliffhanger. Instead of juggling a dozen modifiers, you just roll two d20s and take the better (or worse) one, which shakes out to about a +5 or -5 vibe shift. It's like your rogue's got a lucky charm when sneaking past guards or a cursed boot when dodging a fireball. This keeps the game's flow snappy, so you're not stuck crunching numbers when you could be roleplaying a dramatic speech to charm a dragon or bluffing your way out of a bandit ambush."
          ),
          p(
            "5e's world is built for storytelling, not just stat sheets, and that's why it's the king of game nights. The rules are flexible enough for your DM to whip up a haunted forest crawl or a pirate ship heist without needing a PhD in game design. Classes like the warlock let you make shady pacts with cosmic entities, while feats like Tavern Brawler turn your monk into a bar-fighting legend who can knock out goblins with a chair. Whether you're a newbie rolling your first d20 or a veteran plotting a castle siege, 5e's vibe is all about epic moments—like when your party's wizard crits on a fireball and you all cheer like you just won the Super Bowl."
          )
        )
      )
  })
}
  