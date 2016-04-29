lazy val runDynamicContact        = taskKey[Unit]("Run the DynamicContact DSL example.")
lazy val runFormattedMessage      = taskKey[Unit]("Run the FormattedMessage DSL example.")
lazy val runFunctionalReminder    = taskKey[Unit]("Run the FunctionalReminder DSL example.")
lazy val runImperativeSimpleMail  = taskKey[Unit]("Run the ImperativeSimpleMail DSL example.")
lazy val runImplicitMime          = taskKey[Unit]("Run the ImplicitMime DSL example.")
lazy val runEveryControlStructure         = taskKey[Unit]("Run the EveryControlStructure DSL example.")

name := "dsl-project-2016"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "javax.mail" % "mail" % "1.4.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.7"
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"


libraryDependencies += "com.github.philcali" %% "cronish" % "0.1.3"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

mainClass in (Compile, run) := Some("example.SendMailExample")

fullRunTask(runDynamicContact, Compile, "example.builders.DynamicContactBuilding")
fullRunTask(runFormattedMessage, Compile, "example.builders.FormattedMessageBuilding")
fullRunTask(runFunctionalReminder, Compile, "example.builders.FunctionalReminderBuilding")
fullRunTask(runImperativeSimpleMail, Compile, "example.builders.ImperativeSimpleMailBuilding")
fullRunTask(runImplicitMime, Compile, "example.builders.ImplicitMimeBuilding")
fullRunTask(runEveryControlStructure, Compile, "example.scheduler.EveryControlStructure")
