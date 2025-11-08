package examples.webawesome.scroller.example3
  
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
        _.orientation.vertical
      )(
        maxHeight.px := 300,
        p(
          "Superhero movies are the ultimate popcorn-fueled thrill rides, turning comic book pages into cinematic rollercoasters. Back in the early 2000s, films like X-Men and Spider-Man kicked open the door, proving tights and teamwork could pack theaters. Those early flicks leaned on practical effects and heart—like Tobey Maguire's earnest web-slinger saving a train—making us believe a guy in spandex could be a hero. They weren't perfect, but they set the stage for the genre to become a cultural juggernaut."
        ),
        p(
          "By the 2010s, the Marvel Cinematic Universe turned superhero films into a shared saga, like a comic crossover event on steroids. The Avengers in 2012 was a game-changer, tossing Iron Man's snark, Thor's hammer, and Cap's shield into one epic brawl. Directors learned to balance humor, heart, and explosions, while studios figured out how to make every movie feel like a chapter in a bigger story. Even standalone hits like Wonder Woman brought fresh vibes, with Gal Gadot's lasso-wielding warrior stealing hearts and smashing box office records."
        ),
        p(
          "Today, superhero flicks are a global obsession, from Deadpool's chimichanga-fueled chaos to Black Panther's Wakandan pride. They're not just about powers—they're about characters we root for, like Rocket Raccoon's scrappy loyalty or Harley Quinn's wild energy. Fans dissect trailers like detectives, theorizing about multiverses and cameos, while memes of sad Affleck or dancing Groot flood the internet. Whether it's a gritty Joker origin or a cosmic Guardians adventure, these movies keep us glued to our seats, dreaming of heroism and one-liners that'd make even Tony Stark jealous."
        )
      )
  })
}
  