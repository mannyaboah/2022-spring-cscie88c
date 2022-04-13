package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class TrigUtilsTest extends StandardTest {

  "TrigUtils" when {

    "calling sin" should {

      "return the correct value for sin 90 in radians 😄" in {
        val res = TrigUtils.sinDegrees(90)
        (math floor res * 100) / 100 shouldBe 0.89
      }

      "return the correct value for sin 0 in radians 😄" in {
        TrigUtils.sinDegrees(0) shouldBe 0
      }
    }

    "calling cosin" should {
      "return the correct value for cosin 90 in radians 😄" in {
        val res = TrigUtils.cosDegrees(90)
        (math floor res * 100) / 100 shouldBe -0.45
      }

      "return the correct value for cosin 0 in radians 😄" in {
        TrigUtils.cosDegrees(0) shouldBe 1
      }
    }

    "calling squared" should {
      "return the square of 8 😄" in {
        TrigUtils.squared(8) shouldBe 64
      }
    }
  }
}
