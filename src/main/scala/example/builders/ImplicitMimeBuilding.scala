package example.builders

import builders.implicitBuilder.ImplicitMimeMessageBuilder

object ImplicitMimeBuilding extends App with ImplicitMimeMessageBuilder {
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

  send
  println("Sent")
}