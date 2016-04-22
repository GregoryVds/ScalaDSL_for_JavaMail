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
    implicit val properties : SysProperties = SysProperties()
    implicit var msg : MimeMessage = null
  }

  class MimeMessage(properties : SysProperties){
    import javax.mail.internet.InternetAddress
    import javax.mail.{Session, SendFailedException}
    import javax.mail.Message.RecipientType
    import javax.mail.Transport
    import utils.Utils._

    val session = Session.getDefaultInstance(properties.properties)
    val message = new javax.mail.internet.MimeMessage(session)

    def to(to : Seq[String])      = message addRecipients (RecipientType.TO, seqToAddresses(to))
    def cc(cc: Seq[String])       = message addRecipients (RecipientType.CC, seqToAddresses(cc))
    def bcc(bcc: Seq[String])     = message addRecipients (RecipientType.BCC, seqToAddresses(bcc))

    def from(from : Seq[String])  = message addFrom seqToAddresses(from)
    def subject(subject : String) = message setSubject subject

    def content(content : Elem)   = message setContent (content.toString, "text/html")
    def content(content : String) = message setContent (content, "text/html")

    def send : MimeMessage        = {
      try{
        Transport send message
      } catch {
        case exc : Exception => println(s"Error : ${exc.getMessage}")
      }

      this
    }

    def ifFailure(body: => Unit) = {
      body
    }
  }

  object MimeMessage {
    def apply(properties : SysProperties) = new MimeMessage(properties)
  }

  /* ALTERNATIVE
  class SysProperties(body: SysProperties => Unit) extends java.util.Properties {
    val properties : mutable.Map[String, String] = System.getProperties

    def apply() = {
      body(this)
      properties
    }

    def add(keyValue : (String, String)) = properties(keyValue._1) = keyValue._2
  }

  object SysProperties {
    def apply(body : SysProperties => Unit) = new SysProperties(body)

    implicit class StringForKey(key: String) {
      def :: (value : String) : (String, String) = {
        (key, value)
      }
    }
  }
  */

  class SysProperties extends java.util.Properties {
    val properties : mutable.Map[String, String] = System.getProperties

    def apply(body : => Unit) = {
      body
      properties
    }
  }

  object SysProperties {
    def apply() = new SysProperties()
  }

}
