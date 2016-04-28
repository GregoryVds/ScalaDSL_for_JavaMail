package example.builders

import builders._
import utils._

/**
  * Created by Greg on 22/04/16.
  */
object ImperativeSimpleMailBuilding extends App {

  val Greg = Contact withName "Greg"    andEmail "gregory.vanderschueren@gmail.com"
  val Leo  = Contact withName "Léonard" andEmail "leonard.julemont@gmaiL.com"

  val properties = defaultProperties

  SimpleMail(properties) to Leo cc Greg withSubject {
    "Hello Greg"
  } withContent {
    <html>
      <body>
        Hello there!
      </body>
    </html>
  } send()

  val mail = SimpleMail(properties) to Leo cc Greg withSubject "Hello Greg" withContent "Hello World!"
  mail.send()
}

