package example.builders

import builders.imperativeBuilder.SimpleMail
import builders.dynamicBuilder.Contact
import utils._

object FormattedMessageBuilding extends App {
  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"
  val Me = Contact withName "Pierre" andEmail "pierre@test.com"

  val properties = Properties add("mail.smtp.host" := "localhost") add("mail.smtp.port" := "1025")

  formatMessage {contact =>
    SimpleMail(properties) to contact.email from Me withSubject {
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
