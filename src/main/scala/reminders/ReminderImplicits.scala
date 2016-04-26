package reminders

/**
  * Created by Greg on 22/04/16.
  */
object ReminderImplicits {
  import SharedTypes._

  type RepWithPeriod = (Rep, Period)
  type RepWithPeriodTarget = (Rep, Period, Target)
  type RepWithPeriodTargetTask = (Rep, Period, Target, Task)

  class RepWithPeriodHelper(rep: Rep) {
    def times_per(period : Period) : RepWithPeriod = (rep, period)
  }

  class RepWithPeriodTargetHelper(tuple: RepWithPeriod) {
    def remind(target: Target) : RepWithPeriodTarget = (tuple._1, tuple._2, target)
  }

  class RepWithPeriodTargetTaskHelper(tuple: RepWithPeriodTarget) {
    def to(task: Task) : RepWithPeriodTargetTask = (tuple._1, tuple._2, tuple._3, task)
  }

  implicit def rep2RepWithPeriodHelper(tup: Rep) : RepWithPeriodHelper =
    new RepWithPeriodHelper(tup)

  implicit def repWithPeriod2RepWithPeriodTargetHelper(tup: RepWithPeriod) : RepWithPeriodTargetHelper =
    new RepWithPeriodTargetHelper(tup)

  implicit def repWithPeriodTarget2RepWithPeriodTargetTaskHelper(tup: RepWithPeriodTarget) : RepWithPeriodTargetTaskHelper =
    new RepWithPeriodTargetTaskHelper(tup)

}
