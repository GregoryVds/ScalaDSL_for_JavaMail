package utils

import builders.dynamicBuilder.Contact

import scala.collection.mutable
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
class MimeMessageWrapper(properties : Properties) {
  import javax.mail.Message.RecipientType
  import javax.mail.{Session, Transport}

  val session = createSession(properties)
  val message = new javax.mail.internet.MimeMessage(session)

  def to(to : String*)       : Unit = message addRecipients (RecipientType.TO, seqToAddresses(to))
  def to(contact: Contact)   : Unit = to(contact.email)
  def cc(cc: String*)        : Unit = message addRecipients (RecipientType.CC, seqToAddresses(cc))
  def cc(contact: Contact)   : Unit = cc(contact.email)
  def bcc(bcc: String*)      : Unit = message addRecipients (RecipientType.BCC, seqToAddresses(bcc))
  def bcc(contact: Contact)  : Unit = bcc(contact.email)
  def from(from : String*)   : Unit = message addFrom seqToAddresses(from)
  def from(contact: Contact) : Unit = from(contact.email)

  def subject(subject : String) : Unit = message setSubject subject
  def content(content : Elem)   : Unit   = message setContent (content.toString, "text/html")
  def content(content : String) : Unit = message setContent (content, "text/html")

  def send : Unit  = Transport send message

  def baseMessage = message

  def createSession(prop : Properties) : Session = Session.getDefaultInstance(prop.properties)
}

object MimeMessageWrapper {
  def apply(properties : Properties) = new MimeMessageWrapper(properties)
}

trait AuthentificationWrapper extends MimeMessageWrapper {
  import javax.mail.{Session, PasswordAuthentication}

  override def createSession(prop : Properties) = {
    Session.getInstance(prop.properties,
      new javax.mail.Authenticator() {
        override def getPasswordAuthentication() : PasswordAuthentication = {
          return new PasswordAuthentication(prop.properties("mail.username"), prop.properties("mail.password"))
        }
      })
  }
}
