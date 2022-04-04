package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
  customerId: String,
  transactionDate: String,
  transactionAmount: Double
)

object CustomerTransaction {
  // add companion object methods below
  def apply(csvString: String): Option[CustomerTransaction] = Try {
    val Array(id, date, amnt) = csvString.split(",")
    CustomerTransaction(id.trim, date.trim, amnt.trim.toDouble)
  }.toOption

  def readFromCSVFile(fileName: String): List[CustomerTransaction] =
    Source
      .fromResource(fileName)
      .getLines()
      .map(CustomerTransaction(_))
      .collect { case Some(ct) => ct }
      .toList

}
