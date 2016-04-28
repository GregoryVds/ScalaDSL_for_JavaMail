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
}
