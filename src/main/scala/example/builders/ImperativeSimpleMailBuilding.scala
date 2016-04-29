package example.builders

import builders._
import builders.imperativeBuilder.Authentication
import utils._
import scala.collection.mutable

/**
  * Created by Greg on 22/04/16.
  */
object ImperativeSimpleMailBuilding extends App {

  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"

  val properties = defaultProperties

  SimpleMail(properties) to Leo cc Greg withSubject "Hi" withContent <h1>Meow</h1> send()

  SimpleMail(properties) from Leo to Greg withSubject {
    "Hello Greg"
  } withContent {
    <html>
      <body>
        Hello there!
      </body>
    </html>
  } send()

  val properties2 = mutable.Map("mail.smtp.host" -> "localhost", "mail.smtp.port" -> "1025", "mail.smtp.auth" -> "true", "mail.username" -> "test", "mail.password" -> "test")

  val mail = new SimpleMail(properties2) with Authentication to Leo from Greg withSubject "Hello Greg" withContent "Hello World!"
  mail.send()
}
