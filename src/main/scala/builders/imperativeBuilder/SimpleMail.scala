package builders.imperativeBuilder

import builders._
import utils._
import scala.xml.Elem
import scala.collection.mutable

/**
  * Created by Greg on 27/04/16.
  */
class SimpleMail(properties : mutable.Map[String, String])  {
  val message = new MimeMessageWrapper(properties)

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

  def send() = message.send()
}

object SimpleMail {
  def apply(properties : mutable.Map[String, String]) = new SimpleMail(properties)
}
