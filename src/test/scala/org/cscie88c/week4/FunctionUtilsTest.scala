package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }
import org.cscie88c.week4.FunctionUtils._

class FunctionUtilsTest extends StandardTest {

  "FunctionUtils" when {
    "calling applyNtimes" should {
      "return the correct values for applying some functon N times 😅" in {
        val testI = (1, 2)
        val testII = (2, 2)
        val testIII = (3, 0)
        val testIV = (4, 2)
        val testV = (5, 0)

        def add2(x: Int): Int = x + 2

        applyNtimes(testI._1)(testI._2)(add2) shouldBe 4
        applyNtimes(testII._1)(testII._2)(add2) shouldBe 8
        applyNtimes(testIII._1)(testIII._2)(add2) shouldBe 6
        applyNtimes(testIV._1)(testIV._2)(add2) shouldBe 16
        applyNtimes(testV._1)(testV._2)(add2) shouldBe 10
      }

    }

    "calling applyNtimesFold" should {
      "return the correct values for applying some functon N times using Fold 😅" in {
        val testI = (1, 2)
        val testII = (2, 2)
        val testIII = (3, 0)
        val testIV = (4, 2)
        val testV = (5, 0)

        def add2(x: Int): Int = x + 2

        applyNtimesFold(testI._1)(testI._2)(add2) shouldBe 4
        applyNtimesFold(testII._1)(testII._2)(add2) shouldBe 8
        applyNtimesFold(testIII._1)(testIII._2)(add2) shouldBe 6
        applyNtimesFold(testIV._1)(testIV._2)(add2) shouldBe 16
        applyNtimesFold(testV._1)(testV._2)(add2) shouldBe 10
      }

    }

    "calling myPositivePower" should {
      "calulate exponents correctly" in {
        myPositivePowerFold(2, 5) shouldBe Math.pow(2, 5)
        myPositivePowerFold(3, 5) shouldBe Math.pow(3, 5)
        myPositivePowerFold(2, 0) shouldBe Math.pow(2, 0)
      }
    }

    "calling defered executer" should {
      "deffer and add 5 😄" in {
        val test = deferredExecutor("test")((x: Int) => x + 5)
        val res = test(5)

        res shouldBe 10
      }
    }
  }
}
