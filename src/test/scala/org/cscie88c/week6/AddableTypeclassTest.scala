package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }
import org.cscie88c.week6.AddableTypeclass._
import org.cscie88c.week6.AddableAggregator._

class AddableTypeclassTest extends StandardTest {

  "AddableAggregator" should {
    "sum a list of integers 😀" in {
      // add your unit tests here
      val intList = List(1, 2, 3, 4, 5, 6)

      sumWithAddable(intList) shouldBe 21

    }
    "sum a list of booleans 😀" in {
      // add your unit tests here
      val boolList = List(true, false, false, true, true, false)
      val boolListI = List(false, false)

      sumWithAddable(boolList) shouldBe true
      sumWithAddable(boolListI) shouldBe false

    }
    "sum a list of employees 😀" in {
      // add your unit tests here
      val employees = List[Employee](
        Employee("Jack Sparrow", 36, 10000),
        Employee("Black Beard", 55, 2000000)
      )

      sumWithAddable(employees) shouldBe Employee(
        "Jack Sparrow & Black Beard",
        91,
        2010000
      )
    }
  }
}
