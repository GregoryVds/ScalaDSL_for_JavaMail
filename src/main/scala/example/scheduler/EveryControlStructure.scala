package example.scheduler

import builders._
import scheduler._
import utils._

/**
  * Created by Greg on 28/04/16.
  */
object EveryControlStructure extends App {
  val Greg = Contact withName "Greg" andDog "Chucky" andAge "25"
  val Leo  = Contact withName "Léonard" andAge "21" andCat "Flappy"
  val Me   = Contact withName "Léonard" andAge "21" andCat "Flappy"

  /*
  every(Second) {
    SimpleMail(defaultProperties) to Leo cc Greg withSubject "Hello Greg" withContent "Hello World!"

    formatMessage {contact =>
      SimpleMail(defaultProperties) to contact from Me withSubject {
        "Hello " + contact("name")
      } withContent {
        <html>
          <body>
            Hello there !
          </body>
        </html>
      } send()
    } to List(Greg, Leo)
  }
  */

  /*
  every(Day).at("10:27"){
    println("Bonjour")
  }.andAt("10:28") {
    println("Encore bonjour")
  } done
  */

  /*
  every(Day) { day =>
    day.at("11:09"){println("Bonjour")}
    day.at("11:10"){println("encore bonjour")}
  }
  */


  /*
  at("11:47"){
    println("Salut")
  } done
  */

  every(Day){day =>
    day.at("12:16"){println("Bonjour")}
  } done

}
