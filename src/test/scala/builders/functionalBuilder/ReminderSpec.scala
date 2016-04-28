package builders.functionalBuilder
import builders._
import utils._
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by Greg on 28/04/16.
  */
class ReminderSpec extends FlatSpec with Matchers {
  val Greg = Contact withName "Greg" andEmail "gregory.vanderschueren@gmail.com"

  "A RepPeriod" should "be buildable from the Every object" in {
    Every.second shouldBe a [RepPeriod]
    Every.minute shouldBe a [RepPeriod]
    Every.hour shouldBe a [RepPeriod]
    Every.day shouldBe a [RepPeriod]
    Every.month shouldBe a [RepPeriod]
    Every.year shouldBe a [RepPeriod]
  }

  "A RepPeriodToContact" should "be buildable from a RepPeriod" in {
    Every.second remind Greg shouldBe a [RepPeriodToContact]
  }

  it should "not be sendable" in {
    "Every.second remind Greg send()" shouldNot compile
  }

  "A RepPeriodToTask" should "be buildable from a RepPeriodToContact" in {
    Every.second remind Greg to "DoSomething" shouldBe a [RepPeriodToTask]
  }

  it should "be sendable" in {
    "Every.second remind Greg to \"DoSomething\" send()" should compile
  }

  "A RepPeriodToRunTime" should "be buildable from a RepPeriodToTask" in {
    Every.day remind Greg to "DoSomething" at "11:00" shouldBe a [RepPeriodToRunTime]
  }

  it should "not be buildable from a RepPeriodToTask with RepPeriod less than day" in {
    an [NoSuchMethodException] should be thrownBy (Every.second remind Greg to "DoSomething" at "11:00")
    an [NoSuchMethodException] should be thrownBy (Every.minute remind Greg to "DoSomething" at "11:00")
    an [NoSuchMethodException] should be thrownBy (Every.hour remind Greg to "DoSomething" at "11:00")
  }

  it should "be sendable" in {
    "Every.day remind Greg to \"DoSomething\" at \"11:00\" send()" should compile
  }

  "A RepPeriodToRunDay" should "be buildable from a RepPeriodToRunTime" in {
    Every.month remind Greg to "DoSomething" at "11:00" on_the "12th" shouldBe a [RepPeriodToRunDay]
  }

  it should "not be buildable from a RepPeriodToTask with RepPeriod less than month" in {
    an [NoSuchMethodException] should be thrownBy (Every.day remind Greg to "DoSomething" at "11:00" on_the "12th")
  }

  it should "be sendable" in {
    "Every.month remind Greg to \"DoSomething\" at \"11:00\" on_the \"12th\" send()" should compile
  }

  "A RepPeriodToMonth" should "be buildable from a RepPeriodToRunDay" in {
    Every.year remind Greg to "DoSomething" at "11:00" on_the "12th" in March shouldBe a [RepPeriodToMonth]
  }

  it should "not be buildable from a RepPeriodToTask with RepPeriod less than year" in {
    an [NoSuchMethodException] should be thrownBy (Every.month remind Greg to "DoSomething" at "11:00" on_the "12th" in November)
  }

  it should "be sendable" in {
    "Every.year remind Greg to \"DoSomething\" at \"11:00\" on_the \"12th\" in March send()" should compile
  }
}
