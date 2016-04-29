package examples.builders

import builders._
import utils._

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
object FormattedMessageBuilding extends App {
  val Greg  = Contact withName "Greg"     andEmail "gregory.vanderschueren@gmail.com"
  val Leo   = Contact withName "Léonard"  andEmail "leonard.julemont@gmaiL.com"
  val Me    = Contact withName "Pierre"   andEmail "pierre@test.com"

  val properties = defaultProperties

  formatMessage { contact =>
    SimpleMail(properties) to contact from Me withSubject {
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
