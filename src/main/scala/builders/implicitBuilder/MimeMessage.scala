package builders.implicitBuilder

import scala.collection.mutable
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
class MimeMessage() {
  import javax.mail.Message.RecipientType
  import javax.mail.{Session, Transport}
  import utils.Utils._

  val properties : mutable.Map[String, String] = System.getProperties

  properties("mail.smtp.host") = "localhost"
  properties("mail.smtp.port") = "1025"

  val session = Session.getDefaultInstance(properties)
  val message = new javax.mail.internet.MimeMessage(session)

  def to(to : Seq[String])      = message addRecipients (RecipientType.TO, seqToAddresses(to))
  def cc(cc: Seq[String])       = message addRecipients (RecipientType.CC, seqToAddresses(cc))
  def bcc(bcc: Seq[String])     = message addRecipients (RecipientType.BCC, seqToAddresses(bcc))

  def from(from : Seq[String])  = message addFrom seqToAddresses(from)
  def subject(subject : String) = message setSubject subject

  def content(content : Elem)   = message setContent (content.toString, "text/html")
  def content(content : String) = message setContent (content, "text/html")

  def send : Unit               = Transport send message
}

object MimeMessage {
  def apply() = new MimeMessage()
}