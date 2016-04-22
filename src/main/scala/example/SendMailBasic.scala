package example

import simplemail._
import simplemail.SysProperties._

object SendMailBasic extends App with SimpleMail {

  /* ALTERNATIVE
  properties { property =>
    property add("mail.smtp.host" :: "localhost")
    property add("mail.smtp.port" :: "1025")
  }
  */

  properties {
    add("mail.smtp.host" := "localhost")
    add("mail.smtp.port" := "1025")
  }

  msg = MimeMessage(properties)

  //to("greg@gmail.com", "jean@hotmail.com")
  to("greg@gmail.com", "leo")
  //cc("leonard@gmail.com")
  //bcc("pierre@gmail.com", "thomas@yahoo.com")

  //from("jony@gmail.com", "marie@gmail.com")
  from("jony@gmail.com")
  subject("My first basic email...")

  content(
    <html>
      <body>
        Hello there!
      </body>
    </html>
  )

  send ifFailure {println("A failure happened")}
  println("Sent")
}
