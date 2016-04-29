package examples.builders

import builders._

object ImplicitMimeBuilding extends App with ImplicitMailBuilder {
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

    send()
    println("Sent")
}