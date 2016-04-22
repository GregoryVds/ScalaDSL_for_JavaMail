package example
import reminders.ReminderImplicitsV2.implicitReminder
import reminders.SharedTypes._

/**
  * Created by Greg on 22/04/16.
  */
object ReminderBuildingV2 extends App with implicitReminder {
  // With this second implem, the order does not matter.
  // We should start with a value... How to start with a method? Since as "remind ..."
  // Only good way I found with with the number of repetition
  10 times_per Week remind Greg to "Walk the dog" at 10
  1 time_per Year at 11 remind Greg to "Walk the dog" at 11
}
