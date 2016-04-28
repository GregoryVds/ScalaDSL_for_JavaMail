package builders.implicitBuilder

import utils.MimeMessageWrapper
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
trait ImplicitMimeMessageBuilder {
  implicit val msg : MimeMessageWrapper = MimeMessageWrapper()

  def to(to : String*)            (implicit msg : MimeMessageWrapper) = msg to(to:_*)
  def cc(cc : String*)            (implicit msg : MimeMessageWrapper) = msg cc(cc:_*)
  def bcc(bcc : String*)          (implicit msg : MimeMessageWrapper) = msg bcc(bcc:_*)

  def from(from : String*)        (implicit msg : MimeMessageWrapper) = msg from(from:_*)
  def subject(subject : String)   (implicit msg : MimeMessageWrapper) = msg subject subject

  def content(content : Elem)     (implicit msg : MimeMessageWrapper) = msg content content
  def content(content : String)   (implicit msg : MimeMessageWrapper) = msg content content

  def send                        (implicit msg : MimeMessageWrapper) = msg.send
}
