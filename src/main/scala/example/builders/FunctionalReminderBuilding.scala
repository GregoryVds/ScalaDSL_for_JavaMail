package example.builders

import builders._
import utils._

/**
  * Created by Greg on 22/04/16.
  */
object FunctionalReminderBuilding extends App {
  val Greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  // To create
  Every.second  remind Greg to "Walk the dog"
  Every.minute  remind Greg to "Walk the dog"
  Every.hour    remind Greg to "Walk the dog"
  Every.day     remind Greg to "Walk the dog" at "10:00"
  Every.month   remind Greg to "Walk the dog" at "10:00" on_the "12th"
  Every.year    remind Greg to "Walk the dog" at "10:00" on_the "12th" in July

  // To actually send, call send() on the returned Tuple
  Every.year remind Greg to "Walk the dog" at "10:00" on_the "12th" in July send()
}