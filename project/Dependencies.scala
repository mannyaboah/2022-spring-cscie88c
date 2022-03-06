import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    // to run scalacheck code snippets in sbt console
    "org.scalacheck" %% "scalacheck" % "1.15.4",
    // for law testing cats based typeclasses
    "org.typelevel" %% "cats-laws" % "2.0.0" % Test,
    "org.typelevel" %% "cats-testkit-scalatest" % "2.1.5" % Test,
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.15" % "1.3.0" % Test
  )

  val circeVersion = "0.13.0"
  val pureconfigVersion = "0.15.0"
  val catsVersion = "2.2.0"

  lazy val core = Seq(
    // cats FP libary
    "org.typelevel" %% "cats-core" % catsVersion,
    // support for JSON formats
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "io.circe" %% "circe-literal" % circeVersion,
    // support for typesafe configuration
    "com.github.pureconfig" %% "pureconfig" % pureconfigVersion,
    // logging
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    // algebird
    "com.twitter" %% "algebird-core" % "0.13.9"
  )
}
