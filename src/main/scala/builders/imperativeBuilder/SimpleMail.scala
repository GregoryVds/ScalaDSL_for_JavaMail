package builders.imperativeBuilder


import builders._
import utils._
import scala.xml.Elem
import scala.collection.mutable

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  *
  * Hight level wrapper around the MimeMessageWrapper to be used by the DSL user.
  */
class SimpleMail(properties : mutable.Map[String, String]) {

  var mimeMsgWrapper : MimeMessageWrapper = createMessage(properties)

  def createMessage(properties : mutable.Map[String, String]) = new MimeMessageWrapper(properties)

  def to(to: String) = {
    mimeMsgWrapper.to(to); this}

  def to(to: Contact) = {
    mimeMsgWrapper.to(to); this}

  def cc(cc: String) = {
    mimeMsgWrapper.cc(cc); this}

  def cc(cc: Contact) = {
    mimeMsgWrapper.cc(cc); this}

  def bcc(bcc: String) = {
    mimeMsgWrapper.bcc(bcc); this}

  def bcc(bcc: Contact) = {
    mimeMsgWrapper.bcc(bcc); this}

  def from(from: String) = {
    mimeMsgWrapper.from(from); this}

  def from(from: Contact) = {
    mimeMsgWrapper.from(from); this}

  def withSubject(subject: String) = {
    mimeMsgWrapper.subject(subject); this}

  def withContent(content: Elem) = {
    mimeMsgWrapper.content(content); this}

  def withContent(content: String) = {
    mimeMsgWrapper.content(content); this}

  def send() = mimeMsgWrapper.send()
}

object SimpleMail {
  def apply(properties : mutable.Map[String, String]) = new SimpleMail(properties)
}

/*
 * Allows the authentication during the creation of the Session. 
 */
trait Authentication extends SimpleMail {
  override def createMessage(properties : mutable.Map[String, String]) = new MimeMessageWrapper(properties) with AuthenticationWrapper
}
