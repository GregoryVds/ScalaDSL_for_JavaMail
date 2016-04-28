package builders.dynamicBuilder

import org.scalatest._

/**
  * Created by Greg on 27/04/16.
  */
class ContactSpec extends FlatSpec with Matchers {

  "A contact" should "accept any methods with single parameter starting with with" in {
    noException should be thrownBy new Contact().withName("Greg")
  }

  it should "raise an exception for methods with single parameter not starting with with" in {
    an [NoSuchMethodException] should be thrownBy new Contact().witName("Greg")
  }

  it should "return the value of a property set previously" in {
    new Contact().withName("Greg").name == "Greg"
    new Contact().withname("Greg").name == "Greg"
    new Contact().withCrAzY_n$Ame("Greg").crAzY_n$Ame == "Greg"
  }

  it should "raise an exception when accessing a property not previously set" in {
    an [NoSuchMethodException] should be thrownBy new Contact().name
  }

  it should "should enforce the first letter of a getter is lowercase" in {
    noException should be thrownBy new Contact().withName("Greg").name
    an [NoSuchMethodException] should be thrownBy new Contact().witName("Greg").Name
  }

  it should "be created from the Contact object" in {
    Contact withName "Greg" shouldBe a [Contact]
  }

  it should "allow chaining setter calls" in {
    val greg = Contact withName "Greg" withDog "Woof"
    greg.name shouldEqual "Greg"
    greg.dog shouldEqual "Woof"
  }

  it should "accept alternate syntax andProperty" in {
    val greg = Contact withName "Greg" andDog "Woof"
    greg.name shouldEqual "Greg"
    greg.dog shouldEqual "Woof"
  }
}
