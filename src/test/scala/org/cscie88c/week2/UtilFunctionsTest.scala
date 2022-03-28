package org.cscie88c.week2

import org.cscie88c.testutils.{ StandardTest }

class UtilFunctionsTest extends StandardTest {

  "Util Funtions" when {
    "executed" should {

      "return maximum" in {
        UtilFunctions.maximum(5, 2) shouldBe 5
      }

      "return average" in {
        UtilFunctions.average(5, 3) shouldBe 4
      }
    }
  }
}
