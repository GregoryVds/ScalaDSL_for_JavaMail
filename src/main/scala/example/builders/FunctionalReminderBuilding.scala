package example.builders

import builders._

/**
  * Created by Greg on 22/04/16.
  */
object FunctionalReminderBuilding extends App {

  val greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  // To create
  Every.second  remind greg to "Walk the dog"
  Every.minute  remind greg to "Walk the dog"
  Every.hour    remind greg to "Walk the dog"
  Every.day     remind greg to "Walk the dog" at "10:00"

  // To actually send, call send()
  Every.month remind greg to "Walk the dog" at "10:00" on_the "12th" send()
}