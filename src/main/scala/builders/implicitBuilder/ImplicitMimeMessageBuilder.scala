package builders.implicitBuilder

import utils._
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
trait ImplicitMimeMessageBuilder {
  // Default properties
  val prop = Properties add("mail.smtp.host" := "localhost") add("mail.smtp.port" := "1025")
  
  implicit val msg : MimeMessageWrapper = MimeMessageWrapper(prop)

  def to(to : String*)            (implicit msg : MimeMessageWrapper) = msg to(to:_*)
  def cc(cc : String*)            (implicit msg : MimeMessageWrapper) = msg cc(cc:_*)
  def bcc(bcc : String*)          (implicit msg : MimeMessageWrapper) = msg bcc(bcc:_*)

  def from(from : String*)        (implicit msg : MimeMessageWrapper) = msg from(from:_*)
  def subject(subject : String)   (implicit msg : MimeMessageWrapper) = msg subject subject

  def content(content : Elem)     (implicit msg : MimeMessageWrapper) = msg content content
  def content(content : String)   (implicit msg : MimeMessageWrapper) = msg content content

  def send                        (implicit msg : MimeMessageWrapper) = msg.send
}
