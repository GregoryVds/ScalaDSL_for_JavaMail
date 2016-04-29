package builders.implicitBuilder

import javax.mail.internet.InternetAddress

import builders._
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, FlatSpec}

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
class ImplicitMimeMessageSpec extends FlatSpec with Matchers with MockFactory with ImplicitMimeMessage {
  val Greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  "An ImplicitMimeMessage" should "allow to call the to() method with a single param" in {
    """to("leo@gmail.com")""" should compile
  }

  it should "allow to call the cc() method with a single param" in {
    """cc("leo@gmail.com")""" should compile
  }

  it should "allow to call the bcc() method with a single param" in {
    """bcc("leo@gmail.com")""" should compile
  }

  it should "allow to call the from() method with a single param" in {
    """from("leo@gmail.com")""" should compile
  }

  it should "allow to call the subject() method with a single param" in {
    """subject("leo@gmail.com")""" should compile
  }

  it should "allow to call the content(String) method with a single param" in {
    """content("leo@gmail.com")""" should compile
  }

  it should "allow to call the content(Elem) method with a single param" in {
    """content("<html></html>")""" should compile
  }

  it should "allow to call the send() method with no param" in {
    """send()""" should compile
  }

  "to()" should "set the recipient" in {
    to("leo@gmail.com")
    /*
    This test is not ideal since it introduces a tight coupling in the tests.
    But for some reason we cannot instantiate the mock object anymore since
    we introduced the createSession() method in the body of the MimeMessage constructor.
    Would be cleaner to mock the unerlying MimeMessage and should for method calls on it.
    Still... it does the job:
    */
    msg.baseMessage.getAllRecipients should contain (new InternetAddress("leo@gmail.com"))
  }

  "cc()" should "set the cc" in {
    cc("leo@gmail.com")
    msg.baseMessage.getAllRecipients should contain (new InternetAddress("leo@gmail.com"))
  }

  "bcc()" should "set the bcc" in {
    bcc("leo@gmail.com")
    msg.baseMessage.getAllRecipients should contain (new InternetAddress("leo@gmail.com"))
  }

  "from()" should "set the from" in {
    from("leo@gmail.com")
    msg.baseMessage.getFrom should contain (new InternetAddress("leo@gmail.com"))
  }

  "subject()" should "set the subject" in {
    subject("Hi")
    msg.baseMessage.getSubject shouldBe "Hi"
  }

  "content(String)" should "set the content" in {
    content("Hi")
    msg.baseMessage.getContent shouldBe "Hi"
  }

  "content(Elem)" should "set the content" in {
    content(<html></html>)
    msg.baseMessage.getContent shouldBe "<html></html>"
  }
}
