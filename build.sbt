name := "dsl-project-2016"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "javax.mail" % "mail" % "1.4.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"

mainClass in (Compile, run) := Some("example.SendMailExample")