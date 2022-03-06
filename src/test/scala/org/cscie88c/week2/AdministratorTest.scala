package org.cscie88c.week2

import org.scalatest.funsuite.AnyFunSuite

// write unit tests for Administrator below
class AdministratorTest extends AnyFunSuite {

  var happyAdmin =
    new Administrator("Professor X", "X@xmenacademy.org", 5000000)
  var sadAdmin = new Administrator("Professor X", "", 50L)

  test("😄 Should contain Name, Email, and Budget") {
    val test =
      s"Name: ${happyAdmin.name}, Email: ${happyAdmin.email}, Budget: $$${happyAdmin.budget}"
    val description = happyAdmin.description
    assert(description.contains(test))
  }

  test("☹️ Should not contain Name, Email, and Budget") {
    val test =
      s"Name: ${happyAdmin.name}, Email: ${happyAdmin.email}, Budget: $$${happyAdmin.budget}"
    val description = sadAdmin.description
    assert(!description.contains(test))
  }

}
