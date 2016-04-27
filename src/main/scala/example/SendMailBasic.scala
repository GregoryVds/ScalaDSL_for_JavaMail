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
    add("mail.smtp.auth" := "true")
    add("mail.username" := "test")
    add("mail.password" := "test")
  }

  msg = new MimeMessage(properties) with Authentification

  //to("greg@gmail.com", "jean@hotmail.com")
  to("greg@gmail.com")
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

  send
  println("Sent")
}
