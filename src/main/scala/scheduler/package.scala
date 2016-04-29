/**
  * Created by Greg on 28/04/16.
  */

import utils._
import cronish.dsl._
import scala.collection.mutable.ArrayBuffer

/*
 * This package allows the scheduling of tasks by the user of the DSL. 
 *
 * Scheduling examples are available in src/main/scala/example/scheduler/EveryControlStructure.scala
 */
package object scheduler {

  /*
   * This class represent a task that should be executed on 'theDay' at 'hour' max 'limit' times.
   * The task to be executed is contained in 'body'.
   * This class is used by Schedule to actually schedule the task.
   */
  class Task(var hour : String = "", var theDay : String = "", var limit : String = "") {
    var body : () => Unit = (() => {})

    def at(hour : String) = {
      this.hour = hour
      this
    }

    def the(theDay : String) = {
      this.theDay = theDay
      this
    }

    def make(body : => Unit) = {
      this.body = (() => body)
      this
    }

    def exactly(limit : Int) = {
      this.limit = limit.toString
      this
    }
  }

  /*
   * This class represent a set of tasks to be exectued every "repPeriod".
   */
  class Schedule(repPeriod : RepPeriod) {
    val tasks = new ArrayBuffer[Task]()

    var onDay = "" // on a specific day of the week
    var onMonth = "" // on a specific month

    def apply(body : Schedule => Unit) = {
      body(this)
      this
    }

    def apply(body : => Unit) = {
      tasks += new Task().make(body)
      this
    }

    def repeated(times : Int) = {
      for( i <- 0 until tasks.length ){
        tasks(i).limit = times.toString
      }
      this
    }

    def at(hour : String) = {
      val task = new Task(hour = hour)
      tasks += task
      task
    }

    def the(theDay : String) = {
      val task = new Task(theDay = theDay)
      tasks += task
      task
    }

    def make(body : => Unit) = {
      val task = new Task()
      task.body = (() => body)
      tasks += task
      task
    }

    def done() : Unit = {
      for( i <- 0 until tasks.length ){
        // Creation of task description
        var taskDesc = "every " + repPeriod.toString.toLowerCase
        if(! onMonth.isEmpty) taskDesc += " in " + onMonth
        if(! onDay.isEmpty) taskDesc += " on " + onDay
        if(! tasks(i).theDay.isEmpty) taskDesc += " on the " + tasks(i).theDay + daySuffix(tasks(i).theDay) + " day"
        if(! tasks(i).hour.isEmpty) taskDesc += " at " + tasks(i).hour

        // Execution of the task (add limit if needed)
        if(tasks(i).limit.isEmpty) task(tasks(i).body()) executes taskDesc
        else job(tasks(i).body()) runs taskDesc exactly ((tasks(i).limit.toInt)+1).times
      }
    }

    def daySuffix(day : String) : String = {
      day match {
        case "1" => "st"
        case "2" => "nd"
        case "3" => "rd"
        case _   => "th"
      }
    }
  }

  /*
   * Method used by the user of the DSL to create scheduled tasks.
   */
  def every(repPeriod : AllPeriod) : Schedule = {
    repPeriod match {
      case period : RepPeriod => new Schedule(period)
      case month : Month => {
        val schedule = new Schedule(Month)
        schedule.onMonth = month.toString
        schedule
      }
      case day : Day => {
        val schedule = new Schedule(Day)
        schedule.onDay = day.toString
        schedule
      }
    }
  }

  /* Using 'at' directly is equivalent to the creation of a task to be executed
   *  at 'time' only once.
   */
  def at(time : String)(body : => Unit) : Schedule = {
    every(Day) {day =>
      day at(time) make(body) exactly(1)
    }
  }
}
