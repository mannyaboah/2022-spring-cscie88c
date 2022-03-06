package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import FunctionUtils._
import math.pow

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    // Problem 1 unit tests
    "calling colorToCode" should {
      "return the correct value for white 😄" in {
        colorToCode("White") shouldBe (255, 255, 255)
      }
      "return the correct value for lime 😄" in {
        colorToCode("Lime") shouldBe (0, 255, 0)
      }
    }

    "calling fizzBuzzString" should {
      "return the correct value 😄" in {
        fizzBuzzString(5) shouldBe "Buzz"
        fizzBuzzString(25) shouldBe "Buzz"
        fizzBuzzString(27) shouldBe "Fizz"
        fizzBuzzString(45) shouldBe "FizzBuzz"
      }
    }

    "calling fizzBuzz" should {
      "return the correct list sequence 😄" in {
        val test = List(
          "1",
          "2",
          "Fizz",
          "4",
          "Buzz",
          "Fizz",
          "7",
          "8",
          "Fizz",
          "Buzz",
          "11",
          "Fizz",
          "13",
          "14",
          "FizzBuzz"
        )
        fizzBuzz(15) shouldBe test
      }
    }
    // Problem 2 unit tests
    "calling tanDegrees" should {
      "return undefined for -90 & 90 degrees input 😟" in {
        tanDegrees.isDefinedAt(90) shouldBe false
        tanDegrees.isDefinedAt(-90) shouldBe false
      }
    }

    "calling totalHighValueTransactions" should {
      "return the correct sum of transactions > 100 😄" in {
        val test: List[CustomerTransaction] = List(
          CustomerTransaction("1", "Jan 1", 150.00),
          CustomerTransaction("2", "Jan 1", 50.00),
          CustomerTransaction("3", "Jan 1", 110.00),
          CustomerTransaction("4", "Jan 1", 120.00),
          CustomerTransaction("5", "Jan 1", 90.00)
        )

        totalHighValueTransactions(test) shouldBe 380.00
      }
    }

    // Problem 3 unit tests
    "calling flip2" should {
      "flip the arguments of a method and return the correct result 😄" in {
        flip2(pow)(5.0, 2.0) shouldBe 32.0
        flip2(pow)(2.0, 6.0) shouldBe 36.0
      }
    }

    "calling simpleList" should {
      "return the first 5 elements of any generic list 😄" in {
        val testI = List(1, 2, 3, 4, 5, 6, 7)
        val testII = List("one", "two", "three", "four", "five", "six", "seven")
        val testIV = List("one", "two", "three")

        val resI = List(1, 2, 3, 4, 5)
        val resII = List("one", "two", "three", "four", "five")
        val resIV = List("one", "two", "three")

        simpleList(testI) shouldBe resI
        simpleList(testII) shouldBe resII
        simpleList(testIV) shouldBe resIV
      }
    }

    // Bonus unit tests
  }

}
