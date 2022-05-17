package org.cscie88c.week2

import org.scalatest.funsuite.AnyFunSuite
import org.cscie88c.week2.UniversityEmployee

// write unit tests for University employee below
class UniversityEmployeeTest extends AnyFunSuite {
  val employeeI: UniversityEmployee = new UniversityEmployee(
    "Tony Stark",
    "mostPowerfulAvenger@starkIndustries.com"
  )
  val employeeII: UniversityEmployee = new UniversityEmployee(
    "Wanda Vision",
    "IheartVision@starkIndustries.com"
  )
  val employeeIII: UniversityEmployee = new UniversityEmployee(
    "Natasha Romanov",
    "bwidow@starkIndustries.com"
  )

  /** Test Names ****************
    */

  test("😄 Name Tony Stark should be in description") {
    val description = employeeI.description
    assert(description.contains("Tony Stark"))
  }

  test("😟 Name Tony Stark should not be in description") {
    val description = employeeII.description
    assert(!description.contains("Tony Stark"))
  }

  test("😄 Name Wanda Vision should be in description") {
    val description = employeeII.description
    assert(description.contains("Wanda Vision"))
  }

  test("😟 Name Wanda Vision should not be in description") {
    val description = employeeI.description
    assert(!description.contains("Wanda Vision"))
  }

  test("😄 Name Natasha Romanov should be in description") {
    val description = employeeIII.description
    assert(description.contains("Natasha Romanov"))
  }

  test("😟 Name Natasha Romanov should not be in description") {
    val description = employeeII.description
    assert(!description.contains("Natasha Romanov"))
  }

  /** Test Emails ****************
    */

  test(
    "😄 Email mostPowerfulAvenger@starkIndustries.com should be in description"
  ) {
    val description = employeeI.description
    assert(description.contains("mostPowerfulAvenger@starkIndustries.com"))
  }

  test(
    "😟 Email mostPowerfulAvenger@starkIndustries.com should not be in description"
  ) {
    val description = employeeII.description
    assert(!description.contains("mostPowerfulAvenger@starkIndustries.com"))
  }

  test("😄 Email IheartVision@starkIndustries.com should be in description") {
    val description = employeeII.description
    assert(description.contains("IheartVision@starkIndustries.com"))
  }

  test(
    "😟 Email IheartVision@starkIndustries.com should not be in description"
  ) {
    val description = employeeI.description
    assert(!description.contains("IheartVision@starkIndustries.com"))
  }

  test("😄 Email bwidow@starkIndustries.com  should be in description") {
    val description = employeeIII.description
    assert(description.contains("bwidow@starkIndustries.com"))
  }

  test("😟 Email bwidow@starkIndustries.com  should be in description") {
    val description = employeeII.description
    assert(!description.contains("bwidow@starkIndustries.com"))
  }
}
