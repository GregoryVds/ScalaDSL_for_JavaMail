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

}