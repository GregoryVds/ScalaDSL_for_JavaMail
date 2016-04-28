package builders.functionalBuilder

import builders._
import utils._
import cronish.dsl._

/**
  * Created by Greg on 22/04/16.
  *
  * FUNCTIONAL BUILDER:
  * Nothing extraordinary here, but only an interesting functional way to build an object.
  * We progressively build a n-tuple via a successive application of implicit conversions.
  * We are only using immutable structures (tuple) to create our reminder object.
  * This however has the drawback of imposing the order of method calls.

  * Every.{second, minute, hour}  remind Greg to "Walk the dog"
  * Every.day     remind Greg to "Walk the dog" at "10:00"
  * Every.month   remind Greg to "Walk the dog" at "10:00" on_the "12th"
  * Every.year    remind Greg to "Walk the dog" at "10:00" on_the "12th" in July
  */

trait Reminder {
  // Public Stuff

  type RepPeriodToContact   = (RepPeriod, Contact)
  type RepPeriodToTask      = (RepPeriod, Contact, Task)
  type RepPeriodToRunTime   = (RepPeriod, Contact, Task, RunTime)
  type RepPeriodToRunDay    = (RepPeriod, Contact, Task, RunTime, RunDay)
  type RepPeriodToMonth     = (RepPeriod, Contact, Task, RunTime, RunDay, Month)

  implicit def withRepPeriod2Contact(t: RepPeriod): AddContact      = new AddContact(t)
  implicit def withContactToTask(t: RepPeriodToContact): AddTask    = new AddTask(t)
  implicit def withTask2RunTime(t: RepPeriodToTask): AddRunTime     = new AddRunTime(t)
  implicit def withRunTime2RunDay(t: RepPeriodToRunTime): AddRunDay = new AddRunDay(t)
  implicit def withRunDay2Month(t: RepPeriodToRunDay): AddMonth     = new AddMonth(t)

  object Every {
    def second  = Second
    def minute  = Minute
    def hour    = Hour
    def day     = Day
    def month   = Month
    def year    = Year
  }

  // Private Stuff
  private case class ReminderWrapper(repPeriod: RepPeriod, contact: Contact, task: Task,
                      runTime: RunTime = "", runDay: RunDay = "", runMonth: Month = MonthNone)

  class AddContact(t: RepPeriod) {
    def remind(contact: Contact): RepPeriodToContact = (t, contact)
  }

  class AddTask(t: RepPeriodToContact) {
    def to(task: Task): RepPeriodToTask = {
      val newT = (t._1, t._2, task)
      newT._1 match {
        case Second | Minute | Hour => send(ReminderWrapper(t._1, t._2, task)); newT
        case _ => newT
      }
    }
  }

  class AddRunTime(t: RepPeriodToTask) {
    def at(time: RunTime): RepPeriodToRunTime = {
      val newT = (t._1, t._2, t._3, time)
      newT._1 match {
        case Second | Minute | Hour => tooLate
        case Day => send(ReminderWrapper(t._1, t._2, t._3, time)); newT
        case _ => newT
      }
    }
  }

  class AddRunDay(t: RepPeriodToRunTime) {
    def on_the(day: RunDay): RepPeriodToRunDay = {
      val newT = (t._1, t._2, t._3, t._4, day)
      newT._1 match {
        case Second | Minute | Hour | Day => tooLate
        case Month => send(ReminderWrapper(t._1, t._2, t._3, t._4, day)); newT;
        case _ => newT
      }
    }
  }

  class AddMonth(t: RepPeriodToRunDay) {
    def in(month: Month): RepPeriodToMonth = {
      val newT = (t._1, t._2, t._3, t._4, t._5, month)
      newT._1 match {
        case Second | Minute | Hour | Day | Month => tooLate
        case Year => send(ReminderWrapper(t._1, t._2, t._3, t._4, t._5, month)); newT;
      }
    }
  }

  private def send(t: ReminderWrapper): Unit = {
    val mail: SimpleMail = new SimpleMail(utils.defaultProperties) to t.contact.email withSubject "Reminder" withContent t.task
    var taskDesc = "every " + t.repPeriod.toString.toLowerCase
    if (!t.runTime.isEmpty)      taskDesc += " at " + t.runTime
    if (!t.runDay.isEmpty)       taskDesc += " on the " + t.runDay + " day"
    if (t.runMonth != MonthNone) taskDesc += " in " + t.runMonth
    task { mail.send } executes taskDesc
  }

  private def tooLate = throw new NoSuchMethodException("Reminder already sent!")
}