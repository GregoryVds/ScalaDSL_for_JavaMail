package example.builders

import builders.dynamicBuilder.Contact
import builders.imperativeBuilder.{SimpleMail, Authentification}
import utils._

/**
  * Created by Greg on 22/04/16.
  */
object ImperativeSimpleMailBuilding extends App {

  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"

  val properties = Properties add("mail.smtp.host" := "localhost") add("mail.smtp.port" := "1025")

  SimpleMail(properties) to Leo cc Greg withSubject {
    "Hello Greg"
  } withContent {
    <html>
      <body>
        Hello there!
      </body>
    </html>
  } send

  val properties2 = Properties add("mail.smtp.host" := "localhost") add("mail.smtp.port" := "1025") add("mail.smtp.auth" := "true") add("mail.username" := "test") add("mail.password" := "test")

  val mail = new SimpleMail(properties2) with Authentification to Leo cc Greg withSubject "Hello Greg" withContent "Hello World!"
  mail.send
}
