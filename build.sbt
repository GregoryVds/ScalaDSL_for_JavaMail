lazy val runSendMailBasic = taskKey[Unit]("Run the sendMailBasic DSL example.")

name := "dsl-project-2016"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "javax.mail" % "mail" % "1.4.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.7"


libraryDependencies += "com.github.philcali" %% "cronish" % "0.1.3"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

mainClass in (Compile, run) := Some("example.SendMailExample")

fullRunTask(runSendMailBasic, Compile, "example.SendMailBasic")
