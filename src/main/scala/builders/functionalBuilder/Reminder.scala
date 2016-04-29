package builders.functionalBuilder

import builders._
import utils._
import cronish.dsl._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  *
  * FUNCTIONAL BUILDER:
  * Nothing extraordinary here, but only an interesting functional way to build an object.
  * We progressively build a n-tuple via a successive application of implicit conversions.
  * We are only using immutable structures (tuple) to create our reminder object.
  * This however has the drawback of imposing the order of method calls.

  * Every.{second, minute, hour}  remind Greg to "Walk the dog"
  * Every.day     remind Greg to "Walk the dog" at "10:00"
  * Every.month   remind Greg to "Walk the dog" at "10:00" on_the "12th"
  * Every.year    remind Greg to "Walk the dog" at "10:00" on_the "12th" of July
  */

trait Reminder {
  // Public Stuff

  type RepPeriodToContact   = (RepPeriod, Contact)
  type RepPeriodToTask      = (RepPeriod, Contact, Task)
  type RepPeriodToRunTime   = (RepPeriod, Contact, Task, RunTime)
  type RepPeriodToRunDay    = (RepPeriod, Contact, Task, RunTime, RunDay)
  type RepPeriodToMonth     = (RepPeriod, Contact, Task, RunTime, RunDay, Month)

  implicit def RepPeriodToTask2ReminderWrapper(t: RepPeriodToTask): ReminderWrapper       = new ReminderWrapper(t._1, t._2, t._3)
  implicit def RepPeriodToRunTime2ReminderWrapper(t: RepPeriodToRunTime): ReminderWrapper = new ReminderWrapper(t._1, t._2, t._3, t._4)
  implicit def RepPeriodToRunDay2ReminderWrapper(t: RepPeriodToRunDay): ReminderWrapper   = new ReminderWrapper(t._1, t._2, t._3, t._4, t._5)
  implicit def RepPeriodToMonth2ReminderWrapper(t: RepPeriodToMonth): ReminderWrapper     = new ReminderWrapper(t._1, t._2, t._3, t._4, t._5, t._6 )

  object Every {
    def second  = Second
    def minute  = Minute
    def hour    = Hour
    def day     = Day
    def month   = Month
    def year    = Year
  }

  // Private Stuff
  case class ReminderWrapper(repPeriod: RepPeriod, contact: Contact, taskString: Task,
                      runTime: RunTime = "", runDay: RunDay = "", runMonth: Month = MonthNone) {
    def schedule() = {
      val mail: SimpleMail = new SimpleMail(utils.defaultProperties) to contact.email withSubject "Reminder" withContent taskString
      var taskDesc = "every " + repPeriod.toString.toLowerCase
      if (!runTime.isEmpty)      taskDesc += " at " + runTime
      if (!runDay.isEmpty)       taskDesc += " on the " + runDay + " day"
      if (runMonth != MonthNone) taskDesc += " in " + runMonth
      task { mail.send() } executes taskDesc
    }
  }

  implicit class AddContact(t: RepPeriod) {
    def remind(contact: Contact): RepPeriodToContact = (t, contact)
  }

  implicit class AddTask(t: RepPeriodToContact) {
    def to(task: Task): RepPeriodToTask = {
      val newT = (t._1, t._2, task)
      newT._1 match {
        case Second | Minute | Hour => newT
        case _ => newT
      }
    }
  }

  implicit class AddRunTime(t: RepPeriodToTask) {
    def at(time: RunTime): RepPeriodToRunTime = {
      val newT = (t._1, t._2, t._3, time)
      newT._1 match {
        case Second | Minute | Hour => invalidCombo
        case _ => newT
      }
    }
  }

  implicit class AddRunDay(t: RepPeriodToRunTime) {
    def on_the(day: RunDay): RepPeriodToRunDay = {
      val newT = (t._1, t._2, t._3, t._4, day)
      newT._1 match {
        case Second | Minute | Hour | Day => invalidCombo
        case _ => newT
      }
    }
  }

  implicit class AddMonth(t: RepPeriodToRunDay) {
    def of(month: Month): RepPeriodToMonth = {
      val newT = (t._1, t._2, t._3, t._4, t._5, month)
      newT._1 match {
        case Second | Minute | Hour | Day | Month => invalidCombo
        case Year => newT
      }
    }
  }

  private def invalidCombo = throw new NoSuchMethodException("Invalid combo!")
}
