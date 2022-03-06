package org.cscie88c.week2

import org.scalatest.funsuite.AnyFunSuite

// write unit tests for Faculty below
class FacultyTest extends AnyFunSuite {

  var happyFaculty =
    new Faculty("Beast", "daBeastie@xmenacademy.org", "CHEM 302")
  var sadFaculty = new Faculty("Beast", "", "CHEM")

  test("😄 Should contain email, name and courseID") {
    val test =
      s"Name: ${happyFaculty.name}, Email: ${happyFaculty.email}, Course: ${happyFaculty.courseId}"
    val description = happyFaculty.description
    assert(description.contains(test))
  }

  test("☹️ Should not contain email, name and courseID") {
    val test =
      s"Name: ${happyFaculty.name}, Email: ${happyFaculty.email}, Course: ${happyFaculty.courseId}"
    val description = sadFaculty.description
    assert(!description.contains(test))
  }

}
