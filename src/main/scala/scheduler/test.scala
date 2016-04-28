package scheduler
import cronish._
import cronish.dsl._

/**
  * Created by Greg on 28/04/16.
  */
object test extends App {


  val helloWorld = task {
    println("Hello World!")
  }

  helloWorld executes "every 15 seconds"
}
