package example

import simplemail._

object SendMailBasic extends App with SimpleMail {
  to("greg@gmail.com")
  cc("leonard@gmail.com")
  bcc("pierre@gmail.com")

  from("jony@gmail.com")
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
