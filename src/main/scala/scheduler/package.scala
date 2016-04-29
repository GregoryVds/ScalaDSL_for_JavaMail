/**
  * Created by Greg on 28/04/16.
  */

import utils._
import cronish.dsl._
import scala.collection.mutable.ArrayBuffer

package object scheduler {

  /*
  def every(repPeriod: RepPeriod)(body : => Unit) = {
    val t = task { body }
    t executes "every "+ repPeriod.toString.toLowerCase
  }
  */

  class Time(var hour : String = "", var limit : String = "") {
    def exactly(limit : Int) : Time = {
      this.limit = limit.toString
      this
    }
  }

  class Schedule(repPeriod : RepPeriod) {
    val tasks = new ArrayBuffer[() => Unit]()
    val timeForTasks = new ArrayBuffer[Time]()

    def apply(body : Schedule => Unit) = {
      body(this)
      this
    }

    def at(hour : String)(body : => Unit) = {
      tasks += (() => body)
      val time = new Time(hour = hour)
      timeForTasks += time
      time
    }

    def repeated(times : Int) = {
      for( i <- 0 until tasks.length ){
        timeForTasks(i).limit = times.toString
      }
    }

    def done() : Unit = {
      for( i <- 0 until tasks.length ){
        var taskDesc = "every " + repPeriod.toString.toLowerCase
        if(! timeForTasks(i).hour.isEmpty) taskDesc += " at " + timeForTasks(i).hour

        if(timeForTasks(i).limit.isEmpty) task(tasks(i)) executes taskDesc
        else {
          println(taskDesc)
          job(tasks(i)) runs taskDesc exactly (timeForTasks(i).limit.toInt).times
        }
      }
    }
  }

  def every(repPeriod : RepPeriod) = new Schedule(repPeriod)

  def at(time : String)(body : => Unit) = {
    every(Day) {day =>
      day.at(time)(body) exactly(1)
    }
  }

  /*
  class Schedule(repPeriod: RepPeriod){
    val tasks = new ArrayBuffer[CronTask]()
    val timeForTasks = new ArrayBuffer[String]()

    def execute() = {
      for( i <- 0 until tasks.length ){
        tasks(i) executes "every " + repPeriod.toString.toLowerCase + " at " + timeForTasks(i)
      }
    }

    def addTask(body : => Unit, time : String) = {
      tasks += task(body)
      timeForTasks += time
    }
  }
  object Schedule{
    def apply(repPeriod: RepPeriod) = new Schedule(repPeriod)
  }

  def every(repPeriod: RepPeriod) = new SchedulerWrapper(repPeriod)

  def at(time : String) = new SchedulerWrapper(null)

  class SchedulerWrapper(repPeriod : RepPeriod) {
    var schedule = Schedule(repPeriod)


    def apply(body : => Unit) = {
      schedule.addTask(body, "00:00")
      this
    }

    def at(time : String)(body : => Unit)  : SchedulerWrapper = {
      schedule.addTask(body, time)
      this
    }

    def andAt(time : String)(body : => Unit) : SchedulerWrapper = at(time)(body)

    def done() : Unit = schedule.execute
  }
  */

  /* Constructs to create

    every(Day) {
      // Do something
    }

    at("10:15") {
      // Do something
    }

    and combinaisons/nested

    every(Friday) {
      at("8:00") {
        // Do something
      }
      at("22:00") {
        // Do something else
      }
    }

    every(Hour) {
      // Do something
    } repeated(Twice)

      repeated(10 times) ?


    every(Friday) {
      // Do something
    } startingIn(now + 3.weeks)   # Operator overload here


   */
}
