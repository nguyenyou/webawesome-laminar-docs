package examples.webawesome.scroller.example1
  
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
      div(
        styleTag("""
          #scroller__overview {
            table {
              margin-block: 0;
            }
      
            th,
            td {
              white-space: nowrap;
            }
      
            th:nth-child(5),
            td:nth-child(5) {
              min-width: 50ch;
              white-space: wrap;
            }
          }
        """),
        Scroller()(
          idAttr := "scroller__overview",
          table(
            tr(
              th("Party Role"),
              th("Combat Style"),
              th("Group Size"),
              th("Campaign Setting"),
              th("Signature Traits")
            ),
            tr(
              td("Warrior"),
              td("Melee Tank"),
              td("1-2"),
              td("Forgotten Realms"),
              td("Plate-armored swordmaster who taunts foes.")
            ),
            tr(
              td("Rogue"),
              td("Stealth Striker"),
              td("1"),
              td("Eberron"),
              td("Shadowy lockpick with daggers and a secret gold stash.")
            ),
            tr(
              td("Wizard"),
              td("Spell Slinger"),
              td("1"),
              td("Greyhawk"),
              td("Robe-clad mage hurling fireballs from a messy spellbook.")
            ),
            tr(
              td("Cleric"),
              td("Divine Support"),
              td("1"),
              td("Ravnica"),
              td("Holy healer with a glowing amulet and sneaky ale habit.")
            ),
            tr(
              td("Bard"),
              td("Charisma King"),
              td("1"),
              td("Dragonlance"),
              td("Lute-playing charmer with magical songs and bad puns.")
            )
          )
        )
      )
  })
}
  