package example.builders

import builders.dynamicBuilder.Contact
import builders.functionalBuilder.Reminder

/**
  * Created by Greg on 22/04/16.
  */
object FunctionalReminderBuilding extends App {
  import Reminder._
  import utils.Types._

  val greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  10.minutes before 10 remind greg to "Walk the dog" on Tuesday
  1.hour after 8 remind greg to "Buy a dog" on_the 5 of December
  at(8) remind greg to "Buy a dog" on_the 5 of December
}