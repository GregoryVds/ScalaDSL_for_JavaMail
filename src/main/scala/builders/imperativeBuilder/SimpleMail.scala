package builders.imperativeBuilder

import builders.dynamicBuilder.Contact
import utils.MimeMessageWrapper
import scala.xml.Elem

/**
  * Created by Greg on 27/04/16.
  */
class SimpleMail  {
  val message = new MimeMessageWrapper()

  def to(to: String) = {
    message.to(to); this}

  def to(to: Contact) = {
    message.to(to); this}

  def cc(cc: String) = {
    message.cc(cc); this}

  def cc(cc: Contact) = {
    message.cc(cc); this}

  def bcc(bcc: String) = {
    message.bcc(bcc); this}

  def bcc(bcc: Contact) = {
    message.bcc(bcc); this}

  def from(from: String) = {
    message.from(from); this}

  def from(from: Contact) = {
    message.from(from); this}

  def withSubject(subject: String) = {
    message.subject(subject); this}

  def withContent(content: Elem) = {
    message.content(content); this}

  def withContent(content: String) = {
    message.content(content); this}

  def send = message.send
}

object SimpleMail {
  def to(to: String)      = new SimpleMail().to(to)
  def to(to: Contact)     = new SimpleMail().to(to)
  def cc(cc: String)      = new SimpleMail().cc(cc)
  def cc(cc: Contact)     = new SimpleMail().cc(cc)
  def bcc(bcc: String)    = new SimpleMail().bcc(bcc)
  def bcc(bcc: Contact)   = new SimpleMail().bcc(bcc)
  def from(from: String)  = new SimpleMail().from(from)
  def from(from: Contact) = new SimpleMail().from(from)

  def withSubject(subject: String)  = new SimpleMail().withSubject(subject)
  def withContent(content: Elem)    = new SimpleMail().withContent(content)
  def withContent(content: String)  = new SimpleMail().withContent(content)
}
