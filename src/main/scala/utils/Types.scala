package utils

/**
  * @author : Léonard Julémont and Grégory Vander Schueren
  */
trait Types {
  type Task      = String
  type RunTime   = String
  type RunDay    = String
  type RunMonth  = String

  sealed trait AllPeriod

  sealed trait RepPeriod extends AllPeriod
  case object Second  extends RepPeriod
  case object Minute  extends RepPeriod
  case object Hour    extends RepPeriod
  case object Day     extends RepPeriod
  case object Month   extends RepPeriod
  case object Year    extends RepPeriod

  sealed trait Month extends AllPeriod
  case object January   extends Month
  case object February  extends Month
  case object March     extends Month
  case object April     extends Month
  case object May       extends Month
  case object June      extends Month
  case object July      extends Month
  case object August    extends Month
  case object September extends Month
  case object October   extends Month
  case object November  extends Month
  case object December  extends Month
  case object MonthNone extends Month

  sealed trait Day extends AllPeriod
  case object Monday    extends Day
  case object Tuesday   extends Day
  case object Wednesday extends Day
  case object Thursday  extends Day
  case object Friday    extends Day
  case object Saturday  extends Day
  case object Sunday    extends Day
}
