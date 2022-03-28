package org.cscie88c.week7

import scala.io.Source
import scala.util.{ Failure, Success, Try }

object OptionUtils {

  def fileCharCount(fileName: String): Try[Long] =
    Try {
      Source.fromResource(fileName).length
    }

  def charCountAsString(fileName: String): String =
    Try {
      fileCharCount(fileName)
    }.flatten match {
      case Failure(e) =>
        s"error opening file. ${e.getMessage()}"
      case Success(value) => s"number of characters: ${value}"
    }

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      Source
        .fromResource(fileName)
        .mkString
        .split(",")
        .to(LazyList)
    }.toOption
}
