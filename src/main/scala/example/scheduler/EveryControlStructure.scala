package example.scheduler

import builders.dynamicBuilder.Contact
import builders.functionalBuilder.Reminder._
import scheduler._
import utils.Types.{Second, Day}

/**
  * Created by Greg on 28/04/16.
  */
object EveryControlStructure extends App {
  val Greg = Contact withName "Greg" andDog "Chucky" andAge "25"
  val Leo  = Contact withName "Léonard" andAge "21" andCat "Flappy"

  every(Second) {
    println("Sending secondly reminders...")
    10.minutes before 10 remind Greg to "walk the dog"
    at(10) remind Leo to "brush his teeth"
  }

}