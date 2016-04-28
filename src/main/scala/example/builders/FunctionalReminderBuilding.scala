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

  Every.second remind greg to "Walk the dog"
  Every.second remind greg to "Hello"
/*  Every.minute remind greg to "Walk the dog"
  Every.hour remind greg to "Walk the dog"
  Every.day remind greg to "Walk the dog" at "10:00"
  Every.month remind greg to "Walk the dog" at "10:00" on_the "12th"
  Every.year remind greg to "Walk the dog" at "10:00" on_the "12th" in March*/
}