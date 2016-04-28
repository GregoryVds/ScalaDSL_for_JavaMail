package builders.imperativeBuilder

import builders._
import utils._
import org.scalatest.{Matchers, FlatSpec}
import org.scalamock.scalatest._
import scala.xml.Elem
/**
  * Created by Greg on 28/04/16.
  */
class SimpleMailSpec extends FlatSpec with Matchers with MockFactory {
  val Greg                = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"
  val simpleMail          = new SimpleMail(utils.defaultProperties)
  val mockObj             = mock[MimeMessageWrapper]
  val simpleMailWithMock  = new SimpleMail(utils.defaultProperties)
  simpleMailWithMock.mimeMsgWrapper = mockObj

  "A SimpleMail" should "be buildable with apply from a property map" in {
    SimpleMail(utils.defaultProperties) shouldBe a [SimpleMail]
  }

  it should "return itself from to(Contact) setter" in {
    simpleMail to Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when to(Contact) is called" in {
    (mockObj.to(_:Contact)).expects(Greg)
    simpleMailWithMock to Greg
  }

  it should "return itself from to(String) setter" in {
    simpleMail to "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from(Contact) from setter" in {
    simpleMail from Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when from(Contact) is called" in {
    (mockObj.from(_:Contact)).expects(Greg)
    simpleMailWithMock from Greg
  }

  it should "return itself from from(String) setter" in {
    simpleMail from "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from cc(Contact) setter" in {
    simpleMail cc Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when cc(Contact) is called" in {
    (mockObj.cc(_:Contact)).expects(Greg)
    simpleMailWithMock cc Greg
  }

  it should "return itself from cc(String) setter" in {
    simpleMail cc "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from bcc(Contact) setter" in {
    simpleMail bcc Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when bcc(Contact) is called" in {
    (mockObj.bcc(_:Contact)).expects(Greg)
    simpleMailWithMock bcc Greg
  }

  it should "return itself from bcc(String) setter" in {
    simpleMail bcc "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from withSubject() setter" in {
    simpleMail withSubject "Yo" shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when withSubject() is called" in {
    (mockObj.subject(_:String)).expects("Hello")
    simpleMailWithMock withSubject "Hello"
  }

  it should "return itself from withContent(String) setter" in {
    simpleMail withContent "Yo" shouldBe a [SimpleMail]
  }

  it should "return itself from withContent(Elem) setter" in {
    simpleMail withContent <html></html> shouldBe a [SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when withContent(String) is called" in {
    (mockObj.content(_:String)).expects("Hello")
    simpleMailWithMock withContent "Hello"
  }

  it should "call underlying method on MimeMsgWrapper when withContent(Elem) is called" in {
    (mockObj.content(_:Elem)).expects(<html></html>)
    simpleMailWithMock withContent <html></html>
  }
  
}
