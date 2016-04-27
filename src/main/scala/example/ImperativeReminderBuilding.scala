package example
import imperativeReminder.ImperativeReminderBuilding.implicitReminder
import imperativeReminder.SharedTypes._

/**
  * Created by Greg on 22/04/16.
  */
object ImperativeReminderBuilding extends App with implicitReminder {
  10 times_per Week remind Greg to "Walk the dog" at 10
  1 time_per Year at 11 remind Greg to "Walk the dog" at 11
}
