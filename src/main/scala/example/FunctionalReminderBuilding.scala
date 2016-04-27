package example

import functionalReminder.functionalReminder

/**
  * Created by Greg on 22/04/16.
  */
object FunctionalReminderBuilding extends App {
  import functionalReminder._
  import dynamicContact.Contact

  val greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  10.minutes before 10 remind greg to "Walk the dog" on Tuesday
  1.hour after 8 remind greg to "Buy a dog" on_the 5 of December
  at(8) remind greg to "Buy a dog" on_the 5 of December
}