package simplemail.implicits

import simplemail.{MimeMessage, SysProperties}
import scala.xml.Elem

trait ImplicitMimeMessage {
  def to(to : String*)          (implicit msg : MimeMessage) = msg to to
  def cc(cc : String*)           (implicit msg : MimeMessage) = msg cc cc
  def bcc(bcc : String*)         (implicit msg : MimeMessage) = msg bcc bcc

  def from(from : String*)       (implicit msg : MimeMessage) = msg from from
  def subject(subject : String) (implicit msg : MimeMessage) = msg subject subject

  def content(content : Elem)   (implicit msg : MimeMessage) = msg content content
  def content(content : String) (implicit msg : MimeMessage) = msg content content

  def send                      (implicit msg : MimeMessage) = msg.send

  def add(keyValue : (String, String))(implicit prop: SysProperties) = prop.properties(keyValue._1) = keyValue._2

  implicit class StringForKey(key: String) {
    def := (value : String) : (String, String) = (key,value)
  }
}
