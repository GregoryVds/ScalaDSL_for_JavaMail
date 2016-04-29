package examples.builders

import builders._
import builders.imperativeBuilder.{Authentication, Signature}
import utils._
import scala.collection.mutable

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
object ImperativeSimpleMailBuilding extends App {
  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com" andSignature "Grégory Vander Schueren \n\n phone : +32000000000"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"
  val properties = defaultProperties

  /*
  The imperative builder is probably this simplest one: by always returning
  the instance after a call to a setter method, it allows to easily build
  an object by chaining method calls in an arbitrary order. We call it imperative
  since we create a single instance of the object that progressively mutates
  at each new method call to finally form the final object.
  */

  // The order of methods calls does not matter:
  SimpleMail(properties) from Leo to Greg withSubject "Hi" withContent <h1>Meow</h1>
  SimpleMail(properties) to Leo from Greg withContent <h1>Meow</h1> withSubject "Hi"

  // To actually send the mail, call send() on it:
  val email = SimpleMail(properties) to Leo from Greg cc Greg withSubject "Hi" withContent <h1>Meow</h1>
  email.send()

  // Note that you can also chain the send() call:
  SimpleMail(properties) to Leo from Greg cc Greg withSubject "Hi" withContent <h1>Meow</h1> send()

  // We can pass raw HTML for the body since it is supported by Scala:
  SimpleMail(properties) from Leo to Greg withSubject {
    "Hello Greg"
  } withContent {
    <html>
      <body>
        This is HTML!
      </body>
    </html>
  } send()

  // We can pass customize the properties and extend the authentication trait to use
  // an SMTP server that requires authentication.
  val properties2 = mutable.Map(
    "mail.smtp.host" -> "localhost",
    "mail.smtp.port" -> "1025",
    "mail.smtp.auth" -> "true",
    "mail.username" -> "test",
    "mail.password" -> "test"
  )

  val mail = new SimpleMail(properties2) with Authentication with Signature to Leo from Greg withSubject "Hello Leo" withContent "Hello World!"
  mail.send()
}
