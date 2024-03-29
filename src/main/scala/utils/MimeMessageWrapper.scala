package utils

import builders._
import scala.collection.mutable
import scala.xml.Elem

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  *
  * Wrapper of javax.mail.internet.MimeMessage. Provides multiple methods to facilitate
  *  the use of javax.mail.internet.MimeMessage.
  */
class MimeMessageWrapper(properties : mutable.Map[String, String] = utils.defaultProperties) {
  import javax.mail.Message.RecipientType
  import javax.mail.{Session, Transport}

  val session = createSession(properties)
  val mimeMsg = new javax.mail.internet.MimeMessage(session) // The actual MimeMessage

  def to(to : String*)          : Unit = mimeMsg addRecipients (RecipientType.TO, seqToAddresses(to))
  def to(contact: Contact)      : Unit = to(contact.email)
  def cc(cc: String*)           : Unit = mimeMsg addRecipients (RecipientType.CC, seqToAddresses(cc))
  def cc(contact: Contact)      : Unit = cc(contact.email)
  def bcc(bcc: String*)         : Unit = mimeMsg addRecipients (RecipientType.BCC, seqToAddresses(bcc))
  def bcc(contact: Contact)     : Unit = bcc(contact.email)
  def from(from : String*)      : Unit = mimeMsg addFrom seqToAddresses(from)
  def from(contact: Contact)    : Unit = from(contact.email)
  def subject(subject : String) : Unit = mimeMsg setSubject subject
  def content(content : Elem)   : Unit = mimeMsg setContent (content.toString, "text/html")
  def content(content : String) : Unit = mimeMsg setContent (content, "text/html")
  def send()                    : Unit  = Transport send mimeMsg

  /* To access base Java object */
  def baseMessage = mimeMsg
  def createSession(arg : mutable.Map[String, String]) : Session = Session.getDefaultInstance(arg)
}

object MimeMessageWrapper {
  def apply(properties : mutable.Map[String, String]) = new MimeMessageWrapper(properties)
}

/*
 * Allows the authentication during the creation of the Session.
 *
 * The property asking for authentication must be present in 'properties'.
 *  'mail.username' and 'mail.password' must also be present in 'properties' and
 *  must be valid creditentials to connect on the host provided in 'properties'.
 */
trait AuthenticationWrapper extends MimeMessageWrapper {
  import javax.mail.{Session, PasswordAuthentication}

  override def createSession(properties : mutable.Map[String, String]) = {
    Session.getInstance(properties,
      new javax.mail.Authenticator() {
        override def getPasswordAuthentication() : PasswordAuthentication = {
          return new PasswordAuthentication(properties("mail.username"), properties("mail.password"))
        }
      })
  }
}
