package example.builders

import builders.imperativeBuilder.SimpleMail
import builders.dynamicBuilder.Contact
import utils.Utils.formatMessage

object FormattedMessageBuilding extends App {
  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"
  val Me = Contact withName "Pierre" andEmail "pierre@test.com"

  formatMessage {contact =>
    SimpleMail to contact from Me withSubject {
      "Hello "
    } withContent {
      <html>
        <body>
          Hello there !
        </body>
      </html>
    } send
  } to List(Greg, Leo)
}
