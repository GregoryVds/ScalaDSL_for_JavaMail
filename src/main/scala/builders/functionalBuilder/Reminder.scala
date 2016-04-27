package builders.functionalBuilder

/**
  * Created by Greg on 22/04/16.
  * FUNCTIONAL BUILDER:
  * Nothing extraordinary here, but only an interesting functional way to build an object.
  * We build progressively a n-tuple via a successive application of implicit conversions.
  * We are only using immutable structures (tuple) to create our reminder object.
  * This however has the drawback of imposing the order of method calls.
  */

object Reminder extends Types {
  import builders.dynamicBuilder._

  type TimeLapseToHour       = (TimeLapse, Hour)
  type TimeLapseToContact    = (TimeLapse, Hour, Contact)
  type TimeLapseToTask       = (TimeLapse, Hour, Contact, Task)
  type TimeLapseToDay        = (TimeLapse, Hour, Contact, Task, Day)
  type TimeLapseToDayNumber  = (TimeLapse, Hour, Contact, Task, DayNumber)
  type TimeLapseToMonth      = (TimeLapse, Hour, Contact, Task, DayNumber, Month)

  class UnsignedTimeLapse(t: TimeLapse) {
    def minutes = t
    def minute  = t
    def hours   = t * 60
    def hour    = t * 60
  }

  class AddHour(t: TimeLapse) {
    def before(hour: Hour) : TimeLapseToHour = (-t, hour)
    def after(hour: Hour) : TimeLapseToHour  = (+t, hour)
  }

  class AddContact(t: TimeLapseToHour) {
    def remind(contact: Contact) : TimeLapseToContact = (t._1, t._2, contact)
  }

  class AddTask(t: TimeLapseToContact) {
    def to(task: Task) : TimeLapseToTask = (t._1, t._2, t._3, task)
  }

  class AddDay(t: TimeLapseToTask) {
    def on(day: Day) : TimeLapseToDay = (t._1, t._2, t._3, t._4, day)
  }

  class AddDayNumber(t: TimeLapseToTask) {
    def on_the(dayNumber: DayNumber) : TimeLapseToDayNumber = (t._1, t._2, t._3, t._4, dayNumber)
  }

  class AddMonth(t: TimeLapseToDayNumber) {
    def of(month: Month) : TimeLapseToMonth = (t._1, t._2, t._3, t._4, t._5, month)
  }

  implicit def int2UnsignedTimeLapse(int: TimeLapse) : UnsignedTimeLapse                    = new UnsignedTimeLapse(int)
  implicit def int2TimeLapseToHour(int: TimeLapse) : AddHour                                = new AddHour(int)
  implicit def timeLapseToHour2TimeLapseToContact(tup: TimeLapseToHour) : AddContact        = new AddContact(tup)
  implicit def timeLapseToContact2TimeLapseToTask(tup: TimeLapseToContact) : AddTask        = new AddTask(tup)
  implicit def timeLapseToTask2TimeLapseToDay(tup: TimeLapseToTask) : AddDay                = new AddDay(tup)
  implicit def timeLapseToTask2TimeLapseToDayNumber(tup: TimeLapseToTask) : AddDayNumber    = new AddDayNumber(tup)
  implicit def timeLapseToDayNumber2TimeLapseToMonth(tup: TimeLapseToDayNumber) : AddMonth  = new AddMonth(tup)

  def at(hour: Hour) = {
    0.minute before hour
  }
}