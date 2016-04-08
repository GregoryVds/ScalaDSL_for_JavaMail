import simplemail.implicits.ImplicitMimeMessage

import scala.collection.mutable
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.xml.Elem
import java.util.Properties

package object simplemail {

  implicit def map2Properties(map: mutable.Map[String,String]) : java.util.Properties = {
    val props = new Properties()
    map foreach { case (key, value) => props put(key, value) }
    props
  }

  trait SimpleMail extends ImplicitMimeMessage {
    implicit val msg : MimeMessage = MimeMessage()
  }

  class MimeMessage() {
    import javax.mail.internet.InternetAddress
    import javax.mail.Session
    import javax.mail.Message.RecipientType
    import javax.mail.Transport

    val properties : mutable.Map[String, String] = System.getProperties

    properties("mail.smtp.host") = "localhost"
    properties("mail.smtp.port") = "1025"

    val session = Session.getDefaultInstance(properties)
    val message = new javax.mail.internet.MimeMessage(session)

    def to(to : String)           = message addRecipients (RecipientType.TO, to)
    def cc(cc: String)            = message addRecipients (RecipientType.CC, cc)
    def bcc(bcc: String)          = message addRecipients (RecipientType.BCC, bcc)

    def from(from : String)       = message setFrom new InternetAddress(from)
    def subject(subject : String) = message setSubject subject

    def content(content : Elem)   = message setContent (content.toString, "text/html")
    def content(content : String) = message setContent (content, "text/html")

    def send : Unit               = Transport send message
  }

  object MimeMessage {
    def apply() = new MimeMessage()
  }
}
