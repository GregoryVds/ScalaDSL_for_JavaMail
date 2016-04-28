package example.scheduler

import builders.dynamicBuilder.Contact
import builders.functionalBuilder.Reminder._
import builders.imperativeBuilder.SimpleMail
import scheduler._
import utils.Types.{Second, Day}
import utils._

/**
  * Created by Greg on 28/04/16.
  */
object EveryControlStructure extends App {
  val Greg = Contact withName "Greg" andDog "Chucky" andAge "25"
  val Leo  = Contact withName "Léonard" andAge "21" andCat "Flappy"
  val Me   = Contact withName "Léonard" andAge "21" andCat "Flappy"



  every(Second) {

    println("Sending secondly reminders...")

    SimpleMail to Leo cc Greg withSubject "Hello Greg" withContent "Hello World!"

    formatMessage {contact =>
      SimpleMail to contact from Me withSubject {
        "Hello " + contact("name")
      } withContent {
        <html>
          <body>

            Hello there !
          </body>
        </html>
      } send
    } to List(Greg, Leo)


  }

  println("Done")
}
