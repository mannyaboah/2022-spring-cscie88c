package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import Streams._

class StreamsTest extends StandardTest {

  // Bonus problem unit tests
  "Streams" when {
    val test = LazyList[Dog](
      Dog("dog-1", 2, true),
      Dog("dog-2", 4, false),
      Dog("dog-3", 3, true),
      Dog("dog-4", 8, false),
      Dog("dog-5", 10, true)
    )

    "calling healthyDogs" should {
      "return only healthy dogs 😄" in {
        val res = healthyDogs(test).take(5).map(_.hasCurrentShots == true)
        res.size shouldBe 3
        res(0) shouldBe true
        res(1) shouldBe true
        res(2) shouldBe true
      }
    }

    "calling averageHealthyAge" should {
      "return the average age for only healthy dogs 😄" in {
        val resAverage = averageHealthyAge(test, 3)
        resAverage shouldBe 5.0
      }
    }
  }
}
