package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Failure, Success, Try }
import scala.io.Source
import org.cscie88c.config.{ ConfigUtils }
import pureconfig.generic.auto._
import org.cscie88c.config.SimpleApp2Config

// write case class here

// run with: sbt "runMain org.cscie88c.week9.SimpleApp2"
object SimpleApp2 extends LazyLogging {

  val SIMPLE_APP_CONF_PATH = "org.cscie88c.simpleApp2"

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(_.contains(month))

  def monthCount(implicit conf: SimpleApp2Config): String = Try {
    lineStreamFromFile(conf.filename)
  }.map(lines => monthLines(conf.month)(lines.get).size) match {
    case Failure(exception) =>
      s"No valid transactions found for ${conf.month}. \n${exception}"
    case Success(value) => value.toString()
  }

  def main(args: Array[String]): Unit = {
    implicit val simpleApp2 =
      ConfigUtils.loadAppConfig[SimpleApp2Config](SIMPLE_APP_CONF_PATH)

    logger.info(
      s"The total transactions for ${simpleApp2.month} is $monthCount"
    )

  }
}
