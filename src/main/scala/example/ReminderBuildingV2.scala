package example
import reminders.ReminderImplicitsV2.implicitReminder
import reminders.SharedTypes._

/**
  * Created by Greg on 22/04/16.
  */
object ReminderBuildingV2 extends App with implicitReminder {
  // With this second implem, the order does not matter.

  remind(Greg) to "Walk the dog" at 10 each Month
  each(Month) at 10 remind Greg to "Walk the dog"
  at(10) each Year remind Leonard to "Walk the dog"


  // We should start with a value to avoid parentheses
  // Only good way I found with with the number of repetition
  10 times_per Week remind Greg to "Walk the dog" at 10
  1 time_per Year at 11 remind Greg to "Walk the dog" at 11
}
