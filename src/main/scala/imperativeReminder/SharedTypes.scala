package imperativeReminder

/**
  * Created by Greg on 22/04/16.
  */
object SharedTypes {
  abstract class Period
  case object Day   extends Period
  case object Week  extends Period
  case object Month extends Period
  case object Year  extends Period

  abstract class Target(email: String)
  case object Greg    extends Target("gregory.vanderschueren@gmail.com")
  case object Leonard extends Target("leonard.julemont@student.uclouvain.be")
  case object Me      extends Target("gregory.vanderschueren@gmail.com")

  type Task = String
  type Hour = Int
  type Rep  = Int
  type Quantity = Int
}
