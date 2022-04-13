package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }
import org.cscie88c.week6.Employee._

class EmployeeTest extends StandardTest {
  "Employee" should {

    val employees = List[Employee](
      Employee("Johnny Quest", 22, 100000),
      Employee("Captian Planet", 100, 10),
      Employee("Samantha Quest", 20, 250000),
      Employee("Jack Sparrow", 36, 10000),
      Employee("Black Beard", 55, 2000000)
    )

    "have a default sort order 😀" in {
      // write unit tests here
      val sortedByName = List[Employee](
        Employee("Black Beard", 55, 2000000),
        Employee("Captian Planet", 100, 10),
        Employee("Jack Sparrow", 36, 10000),
        Employee("Johnny Quest", 22, 100000),
        Employee("Samantha Quest", 20, 250000)
      )

      defaultSortEmployees(employees) shouldBe sortedByName

    }

    "sort employees by salary 😀" in {
      // write unit tests here
      val sortedBySalary = List[Employee](
        Employee("Captian Planet", 100, 10),
        Employee("Jack Sparrow", 36, 10000),
        Employee("Johnny Quest", 22, 100000),
        Employee("Samantha Quest", 20, 250000),
        Employee("Black Beard", 55, 2000000)
      )

      sortEmployeesBySalary(employees) shouldBe sortedBySalary
    }
  }
}
