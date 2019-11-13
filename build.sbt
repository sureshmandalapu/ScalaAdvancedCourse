name := "ScalaAdvancedCourse"

version := "0.1"

scalaVersion := "2.12.8"

val appDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.25",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.play" %% "play-json" % "2.7.3"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

libraryDependencies ++= appDependencies ++ testDependencies


mainClass in (Compile, run) := Some("com.tutorials.scala.advanced.PartiallyAppliedFunctions")
