package builders.implicitBuilder

import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
trait ImplicitMimeMessageBuilder {
  implicit val msg : MimeMessage = MimeMessage()

  def to(to : String*)            (implicit msg : MimeMessage) = msg to to
  def cc(cc : String*)            (implicit msg : MimeMessage) = msg cc cc
  def bcc(bcc : String*)          (implicit msg : MimeMessage) = msg bcc bcc

  def from(from : String*)        (implicit msg : MimeMessage) = msg from from
  def subject(subject : String)   (implicit msg : MimeMessage) = msg subject subject

  def content(content : Elem)     (implicit msg : MimeMessage) = msg content content
  def content(content : String)   (implicit msg : MimeMessage) = msg content content

  def send                        (implicit msg : MimeMessage) = msg.send
}
