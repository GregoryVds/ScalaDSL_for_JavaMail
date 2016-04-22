package reminders

/**
  * Created by Greg on 22/04/16.
  */
object ReminderImplicits {
  abstract class Period
  case object Week  extends Period
  case object Day   extends Period
  case object Month extends Period
  case object Year  extends Period

  abstract class Target(email: String)
  case object Greg    extends Target("gregory.vanderschueren@gmail.com")
  case object Leonard extends Target("leonard.julemont@student.uclouvain.be")

  type Task = String
  type Rep = Int
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
