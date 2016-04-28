package utils

import builders.dynamicBuilder.Contact

import scala.collection.mutable
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
class MimeMessageWrapper() {
  import javax.mail.Message.RecipientType
  import javax.mail.{Session, Transport}
  import utils.Utils._

  val properties : mutable.Map[String, String] = System.getProperties

  properties("mail.smtp.host") = "localhost"
  properties("mail.smtp.port") = "1025"

  val session = Session.getDefaultInstance(properties)
  val message = new javax.mail.internet.MimeMessage(session)

  def to(to : String*)        = message addRecipients (RecipientType.TO, seqToAddresses(to))
  def to(contact: Contact) : Unit = to(contact.email)
  def cc(cc: String*)         = message addRecipients (RecipientType.CC, seqToAddresses(cc))
  def cc(contact: Contact) : Unit = cc(contact.email)
  def bcc(bcc: String*)       = message addRecipients (RecipientType.BCC, seqToAddresses(bcc))
  def bcc(contact: Contact) : Unit = bcc(contact.email)
  def from(from : String*)    = message addFrom seqToAddresses(from)
  def from(contact: Contact) : Unit  = from(contact.email)

  def subject(subject : String) = message setSubject subject
  def content(content : Elem)   = message setContent (content.toString, "text/html")
  def content(content : String) = message setContent (content, "text/html")

  def send : Unit               = Transport send message

  def baseMessage = message
}

object MimeMessageWrapper {
  def apply() = new MimeMessageWrapper()
}