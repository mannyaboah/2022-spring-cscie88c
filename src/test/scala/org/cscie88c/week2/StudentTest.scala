package org.cscie88c.week2

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

// write unit tests for Student below

class StudentTest extends AnyWordSpec with Matchers {
  "Student Constructor and Functions" when {
    val testData: List[Student] = List(
      "1,Janet,van Dyne,wasp@avengers.org,female,USA",
      "2,Bruce,Banner,hulk@avengers.org,male,USA"
    ).map(Student(_))

    "Executed" should {
      "Create two Students with respective attributes" in {
        val expected: List[Student] = List(
          new Student(
            1,
            "Janet",
            "van Dyne",
            "wasp@avengers.org",
            "female",
            "USA"
          ),
          new Student(2, "Bruce", "Banner", "hulk@avengers.org", "male", "USA")
        )
        assert(testData.size == expected.size && expected.size == 2)
        assert(testData(0).firstName == expected(0).firstName)
        assert(testData(1).firstName == expected(1).firstName)
      }

      "Should filter based on country" in {
        val expected: List[String] = List(
          "Conrart, Emmy",
          "Chismon, Jesse",
          "Blaxlande, Jocelyn"
        )
        Student.studentNamesByCountry("China") shouldBe expected
      }

      "Should filter based on country and return count" in {
        val expected = 2
        Student.studentTotalsByCountry("United States") shouldBe expected
      }
    }
  }
}
