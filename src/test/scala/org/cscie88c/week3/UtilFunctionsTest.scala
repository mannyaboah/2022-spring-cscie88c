package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {
    val testI = UtilFunctions.pythTriplesUpto100(4)
    val testII = UtilFunctions.pythTriplesUpto100(10)
    val testIII = UtilFunctions.pythTriplesUpto100(50)
    "with pythTriplesUpto100 " should {
      "verify elements in list are pythagorean triples 😄" in {
        UtilFunctions.pythTest(testI._1, testI._2, testI._3) shouldBe true
        UtilFunctions.pythTest(testII._1, testII._2, testII._3) shouldBe true
        UtilFunctions.pythTest(testIII._1, testIII._2, testIII._3) shouldBe true
      }
    }
  }
}
