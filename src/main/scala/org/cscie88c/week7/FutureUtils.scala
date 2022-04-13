package org.cscie88c.week7

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future }
import scala.util.Failure
import scala.util.Random
import scala.util.Success

object FutureUtils {

  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future(
    if (applicantId > 0) rnd.between(301, 801) else 0
  )

  def printCreditScore(applicantId: Int): Unit =
    creditScoreAPI(applicantId).onComplete {
      case Success(value) =>
        s"The credit score for applicant Id: $applicantId is: $value"
      case Failure(exception) =>
        s"Cannot get credit score for applicant id: $applicantId at this time. ${exception.getMessage()}"
    }

  def passedCreditCheck(applicantId: Int): Future[Boolean] =
    creditScoreAPI(applicantId).map { score =>
      score > 600.00
    }

  def factorial(n: Int): Int = n match {
    case 0 => 1
    case _ => n * factorial(n - 1)
  }

  def futureFactorial(n: Int): Future[Int] = Future(factorial(n))

  def futurePermuations(n: Int, r: Int): Future[Int] =
    for {
      num1 <- futureFactorial(n)
      num2 <- futureFactorial(n - r)
    } yield num1 / num2

  def futurePermuationsFlatmap(n: Int, r: Int): Future[Int] =
    futureFactorial(n).flatMap { num1 =>
      futureFactorial(n - r).map { num2 =>
        num1 / num2
      }
    }

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] =
    Future
      .sequence(idList.map(creditScoreAPI(_)))
      .map(_.map(_.toDouble).reduce(_ + _) / idList.size)

  def slowMultiplication(x: Long, y: Long): Long = {
    Thread.sleep(1000)
    x * y
  }

  def concurrentFactorial(n: Long): Long = ???

  def sequentialFactorial(n: Long): Long = n match {
    case 0 => 1
    case _ => slowMultiplication(n, sequentialFactorial(n - 1))
  }

}
