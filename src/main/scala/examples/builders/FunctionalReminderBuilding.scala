package examples.builders

import builders._
import utils._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
object FunctionalReminderBuilding extends App {
  val Greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  /*
  Although very similar looking to the imperative builder, our functional builder is
  completely different under the hood. It allows to build an object without any state
  mutation by progressively building an n-tuple through a successive application of
  implicit conversion. It gives the advantages of functional programming but has the
  drawback of imposing a strict order on the method calls. The following example
  builds illustrates how the accumulated Tuple grows at each new method call:

  Every.year remind Leo to "Celebrate" at "23:59" on_the "31th" of December|
  .RepPeriod|..........|..............|..........|.............|...........|
  .(RepPeriod, Contact)|..............|..........|.............|...........|
  ..........(RepPeriod, Contact, Task)|..........|.............|...........|
  ...............(RepPeriod, Contact, Task, Hour)|.............|...........|
  ........................(RepPeriod, Contact, Task, Hour, Day)|...........|
  .............................(RepPeriod, Contact, Task, Hour, Day, Month)|

  For each setter method we define an implicit class that can be instantiated from
  the tuple at the previous step and which implements the setter that returns a new
  larger tuple with the additional state. Instead of defining methods on the object
  being built, it is the implicit conversions that tie together the successive
  invocations of methods defined on helper classes.
  */

  // This time, the order of the methods calls is strict:
  Every.second  remind Greg to "Walk the dog"
  Every.minute  remind Greg to "Walk the dog"
  Every.hour    remind Greg to "Walk the dog"
  Every.day     remind Greg to "Walk the dog" at "10:00"
  Every.month   remind Greg to "Walk the dog" at "10:00" on_the "12th"
  Every.year    remind Greg to "Walk the dog" at "10:00" on_the "12th" of July

  // Not every combo is valid! It will raise an exception for an
  // illegal combo like the following:
  // Every.second remind Greg to "Walk the dog" at "10:00"

  // To actually launch the scheduled reminder, call schedule() on the returned Tuple
  Every.year remind Greg to "Walk the dog" at "10:00" on_the "12th" of July schedule()

  // We can obviously save it in a variable:
  val rem = Every.year remind Greg to "Walk the dog" at "10:00" on_the "12th" of July
  rem.schedule()
}
