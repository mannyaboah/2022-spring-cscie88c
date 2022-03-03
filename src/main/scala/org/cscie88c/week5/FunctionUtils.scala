package org.cscie88c.week5

import scala.math.tan

object FunctionUtils {

  case class CustomerTransaction(
      customerId: String,
      transactionDate: String,
      transactionAmount: Double
    )

  // Problem 1
  def colorToCode(color: String): (Int, Int, Int) = color match {
    case "Black" => (0, 0, 0)
    case "White" => (255, 255, 255)
    case "Lime"  => (0, 255, 0)
    case "Red"   => (255, 0, 0)
    case "Blue"  => (0, 0, 255)
    case "Yello" => (255, 255, 0)
  }

  def fizzBuzzString(n: Int): String = n match {
    case s if s % 15 == 0 => "FizzBuzz"
    case s if s % 3 == 0  => "Fizz"
    case s if s % 5 == 0  => "Buzz"
    case _                => n.toString
  }

  def fizzBuzz(n: Int): List[String] =
    List.tabulate(n)(i => fizzBuzzString(i + 1))

  // Problem 2
  def tanDegrees: PartialFunction[Double, Double] = {
    case n if n != 90 && n != -90 => tan(n.toRadians)
  }

  val greaterThan100: PartialFunction[CustomerTransaction, Double] = {
    case ct if ct.transactionAmount > 100 => ct.transactionAmount
  }

  def totalHighValueTransactions(
      transactionList: List[CustomerTransaction]
    ): Double =
    transactionList.collect(greaterThan100).reduce(_ + _)

  def totalHighValueTransactions_I(
      transactionList: List[CustomerTransaction]
    ): Double =
    transactionList
      .filter(_.transactionAmount > 100.00)
      .map(_.transactionAmount)
      .reduce(_ + _)

  val test: List[CustomerTransaction] = List(
    CustomerTransaction("1", "Jan 1", 150.00),
    CustomerTransaction("2", "Jan 1", 50.00),
    CustomerTransaction("3", "Jan 1", 110.00),
    CustomerTransaction("4", "Jan 1", 120.00),
    CustomerTransaction("5", "Jan 1", 90.00)
  )
  // Problem 3
  def flip2[A, B, C](f: (A, B) => C): (B, A) => C =
    (a: B, b: A) => f(b, a)

  // Write a generic function sampleList parameterized by type A, that returns the first 5 elements of a list of type A.
  def simpleList[A](smallList: List[A]): List[A] = smallList.take(5)

  def deferredExecutor[A, B](name: String)(f: A => B): Int => Int = ???

}
