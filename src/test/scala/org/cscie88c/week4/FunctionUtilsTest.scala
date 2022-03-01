package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

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

        FunctionUtils.applyNtimes(testI._1)(testI._2)(add2) shouldBe 4
        FunctionUtils.applyNtimes(testII._1)(testII._2)(add2) shouldBe 8
        FunctionUtils.applyNtimes(testIII._1)(testIII._2)(add2) shouldBe 6
        FunctionUtils.applyNtimes(testIV._1)(testIV._2)(add2) shouldBe 16
        FunctionUtils.applyNtimes(testV._1)(testV._2)(add2) shouldBe 10
      }

    }

    "calling defered executer should defer 😄" should {
      "deffer and add 5" in {
        val test = FunctionUtils.deferredExecutor("test")((x: Int) => x + 5)
        val res = test(5)

        res shouldBe 10
      }
    }
  }

}
