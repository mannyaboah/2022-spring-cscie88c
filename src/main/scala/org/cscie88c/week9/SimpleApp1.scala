package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging

import scala.io.Source
import scala.util.Failure
import scala.util.Success
import scala.util.Try

// run using: sbt "runMain org.cscie88c.week9.SimpleApp1 <args>"
object SimpleApp1 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.contains(month))

  def janLength(fileName: String): String = Try {
    lineStreamFromFile(fileName)
  }.map(lines => monthLines("Jan")(lines.get).size) match {
    case Failure(exception) =>
      s"No valid transactions found for Jan. \n${exception}"
    case Success(value) => value.toString()
  }

  def main(args: Array[String]): Unit =
    println(janLength(args(0)))

}
