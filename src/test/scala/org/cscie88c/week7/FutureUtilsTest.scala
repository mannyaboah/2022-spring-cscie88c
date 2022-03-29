package org.cscie88c.week7

import org.cscie88c.testutils.{ FuturesTest }
import org.cscie88c.week7.FutureUtils._

import scala.concurrent.Future

class FutureUtilsTest extends FuturesTest {

  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {
    "calling creditScoreAPI" should {
      "return a credit score greater between 300 && 800 😀" in {
        // add unit tests below
        creditScoreAPI(5).map { x =>
          x should be > 300
          x should be <= 800
        }
      }
    }
    "calling futureFactorial" should {
      "return success value  😀" in {
        futureFactorial(4).map { x =>
          x shouldBe 24
        }
      }
    }
    "calling futurePermutation" should {
      "return the correct permutation 😀" in {
        futurePermuations(6, 3).map { p =>
          p shouldBe 120
        }
        futurePermuationsFlatmap(6, 3).map { p =>
          p shouldBe 120
        }
      }
    }
    "calling asyncAverageCreditScore" should {
      "return an average credit score greater between 300 && 800" in {
        val testList = List(2, 4, 6, 8, 10)

        asyncAverageCreditScore(testList).map { sc =>
          sc should be > 300.0
          sc should be <= 800.0
        }
      }
    }
  }
}
