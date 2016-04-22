package example

/**
  * Created by Greg on 22/04/16.
  */
object ReminderBuilding extends App {
  import reminders.ReminderImplicits._
  import reminders.SharedTypes._

  // Here the order matters...
  val test = 10 times_per Week remind Greg to "Walk the dog"

  println(s"${test._1}, ${test._2}, ${test._3}, ${test._4}")
}