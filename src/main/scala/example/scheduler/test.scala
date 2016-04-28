package example.scheduler
import utils.Types._
import cronish.dsl._


/**
  * Created by Greg on 28/04/16.
  */
object test extends App {
  val t = task {
    println("Hello dude")
  }

  t executes "every week at 10:37"
}
