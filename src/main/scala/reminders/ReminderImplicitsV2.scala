package reminders

/**
  * Created by Greg on 22/04/16.
  */
object ReminderImplicitsV2 {
  import SharedTypes._

  class Reminder {
    var period : Period = _
    var target : Target = _
    var task : Task = _
    var hour : Hour = _
    var rep : Rep = 1

    def each(_period: Period) : Reminder = {
      period = _period
      this
    }

    def remind(_target: Target) : Reminder = {
      target = _target
      this
    }

    def to(_task: Task) : Reminder = {
      task = _task
      this
    }

    def at(_hour: Hour) : Reminder = {
      hour = _hour
      this
    }
  }

  trait implicitReminder {
    implicit def int2ReminderHelper(rep : Rep) : ReminderHelper = new ReminderHelper(rep)

    class ReminderHelper(rep : Rep) {
      def times_per(period: Period) = {
        val rem = new Reminder()
        rem.rep = rep
        rem.period = period
        rem
      }
      def time_per(period: Period) = times_per(period)
    }
  }
}
