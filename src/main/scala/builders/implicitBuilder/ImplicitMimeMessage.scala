package builders.implicitBuilder

import utils._
import scala.xml.Elem

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  *
  * Allow the DSL user to create and send a mail in an implicit way. 
  */

trait ImplicitMimeMessage {
  val prop = defaultProperties
  implicit var msg : MimeMessageWrapper = MimeMessageWrapper(prop)

  def to(to : String*)            (implicit msg : MimeMessageWrapper) = msg to(to:_*)
  def cc(cc : String*)            (implicit msg : MimeMessageWrapper) = msg cc(cc:_*)
  def bcc(bcc : String*)          (implicit msg : MimeMessageWrapper) = msg bcc(bcc:_*)
  def from(from : String*)        (implicit msg : MimeMessageWrapper) = msg from(from:_*)
  def subject(subject : String)   (implicit msg : MimeMessageWrapper) = msg subject subject
  def content(content : Elem)     (implicit msg : MimeMessageWrapper) = msg content content
  def content(content : String)   (implicit msg : MimeMessageWrapper) = msg content content
  def send()                      (implicit msg : MimeMessageWrapper) = msg.send()
}
