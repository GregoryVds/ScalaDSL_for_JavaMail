package examples.builders

import builders._

object ImplicitMimeBuilding extends App with ImplicitMailBuilder {
  /*
  The implicit builder is also very simple: it provides a Trait
  that defines an implicit variable of type MimeMessageWrapper
  and several methods which all take a MimeMessageWrapper as an
  implicit second parameters and then delegate the method call
  to this implicit instance.
  */

  to("greg@gmail.com", "jean@hotmail.com")
  cc("leonard@gmail.com")
  bcc("pierre@gmail.com", "thomas@yahoo.com")

  from("jony@gmail.com", "marie@gmail.com")
  subject("My first basic email...")

  content(
    <html>
      <body>
        Hello there!
      </body>
    </html>
  )

  // To actually send the prepared message, just call send()
  send()
}