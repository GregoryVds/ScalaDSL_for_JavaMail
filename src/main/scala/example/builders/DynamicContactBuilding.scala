package example.builders

import builders._

/**
  * Created by Greg on 27/04/16.
  */

object DynamicContactBuilding extends App {

  val greg = Contact withName "Greg" andDog "Chucky" andAge "25"
  val leo  = Contact withName "Léonard" andAge "21" andCat "Flappy"
  val jon  = Contact withName "Jony" withWhatever "Whatever"

  println(s"${greg.name} has dog ${greg.dog} and age ${greg.age}")
}
