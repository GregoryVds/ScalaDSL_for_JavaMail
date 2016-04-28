/**
  * Created by Greg on 28/04/16.
  */

import utils._
import cronish.dsl._

package object scheduler {

  def every(repPeriod: RepPeriod)(body : => Unit) = {
    val t = task { body }
    t executes "every "+ repPeriod.toString.toLowerCase
  }

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