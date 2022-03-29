package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import org.cscie88c.week7.OptionUtils._

import scala.util.Success

class OptionUtilsTest extends StandardTest {
  "OptionUtils" when {
    "calling fileCharCount" should {
      "return the correct number of characters in a valid file 😀" in {

        fileCharCount("data/dirty-retail-data-sample.csv") shouldBe Success(195)
      }

      "return expected failure ☹️" in {
        assert(fileCharCount("test").isFailure)
      }
    }
    "calling charCountString" should {
      "return error opening file ☹️" in {
        charCountAsString("dirty-retail-data-sample.csv-x").contains(
          "error opening file"
        ) shouldBe true
      }

      "Should return string value of count 😀" in {
        charCountAsString("data/dirty-retail-data-sample.csv").contains(
          "number of characters: 195"
        ) shouldBe true
      }
    }
  }

}
