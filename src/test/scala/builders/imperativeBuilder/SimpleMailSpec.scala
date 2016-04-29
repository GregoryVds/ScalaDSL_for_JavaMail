package builders.imperativeBuilder

import javax.mail.internet.InternetAddress
import builders._
import org.scalatest.{Matchers, FlatSpec}
import org.scalamock.scalatest._

/**
  * Created by Greg on 28/04/16.
  */
class SimpleMailSpec extends FlatSpec with Matchers with MockFactory {
  val Greg                = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"
  val simpleMail          = new SimpleMail(utils.defaultProperties)

  "A SimpleMail" should "be buildable with apply from a property map" in {
    SimpleMail(utils.defaultProperties) shouldBe a [SimpleMail]
  }

  it should "return itself from to(Contact) setter" in {
    simpleMail to Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when to(Contact) is called" in {
    /*
    This is not ideal since it introduces a tight coupling in the tests.
    But for some reason we cannot instantiate the mock object anymore since
    we introduced the createSession() method in the body of the MimeMessage constructor.

    The following previous version was much cleaner:
    val mockObj = mock[MimeMessageWrapper]
    val simpleMailWithMock  = new SimpleMail(utils.defaultProperties)
    simpleMailWithMock.mimeMsgWrapper = mockObj
    (mockObj.to(_:Contact)).expects(Greg)
    simpleMailWithMock to Greg


    Stil... it does the job:
    */
    (simpleMail to Greg).mimeMsgWrapper.baseMessage.getAllRecipients should contain (new InternetAddress(Greg.email))
  }

  it should "return itself from to(String) setter" in {
    simpleMail to "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from(Contact) from setter" in {
    simpleMail from Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when from(Contact) is called" in {
    (simpleMail from Greg).mimeMsgWrapper.baseMessage.getFrom should contain (new InternetAddress(Greg.email))
  }

  it should "return itself from from(String) setter" in {
    simpleMail from "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from cc(Contact) setter" in {
    simpleMail cc Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when cc(Contact) is called" in {
    (simpleMail cc Greg).mimeMsgWrapper.baseMessage.getAllRecipients should contain (new InternetAddress(Greg.email))
  }

  it should "return itself from cc(String) setter" in {
    simpleMail cc "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from bcc(Contact) setter" in {
    simpleMail bcc Greg shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when bcc(Contact) is called" in {
    (simpleMail bcc Greg).mimeMsgWrapper.baseMessage.getAllRecipients should contain (new InternetAddress(Greg.email))
  }

  it should "return itself from bcc(String) setter" in {
    simpleMail bcc "gvds@gmail.com" shouldBe a[SimpleMail]
  }

  it should "return itself from withSubject() setter" in {
    simpleMail withSubject "Yo" shouldBe a[SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when withSubject() is called" in {
    (simpleMail withSubject "Yo").mimeMsgWrapper.baseMessage.getSubject shouldBe "Yo"
  }

  it should "return itself from withContent(String) setter" in {
    simpleMail withContent "Yo" shouldBe a [SimpleMail]
  }

  it should "return itself from withContent(Elem) setter" in {
    simpleMail withContent <html></html> shouldBe a [SimpleMail]
  }

  it should "call underlying method on MimeMsgWrapper when withContent(String) is called" in {
    (simpleMail withContent "Yo").mimeMsgWrapper.baseMessage.getContent shouldBe "Yo"
  }

  it should "call underlying method on MimeMsgWrapper when withContent(Elem) is called" in {
    (simpleMail withContent <html></html>).mimeMsgWrapper.baseMessage.getContent shouldBe "<html></html>"
  }
}
