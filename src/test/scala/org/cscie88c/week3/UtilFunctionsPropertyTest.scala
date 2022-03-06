package org.cscie88c.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class UtilFunctionsPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val triplesGen: Gen[(Int, Int, Int)] = for {
    (x, y, z) <- Gen.oneOf(UtilFunctions.pythTriplesUpto100)
  } yield (x, y, z)

  test("😄 mult2 result test") {
    forAll { (x: Int, y: Int) =>
      UtilFunctions.mult2(x, y) shouldBe x * y
    }
  }

  test("😄 mult2 is distributive") {
    forAll { (a: Int, b: Int, c: Int) =>
      UtilFunctions.mult2(a, (b + c)) shouldBe
        UtilFunctions.mult2(a, b) + UtilFunctions.mult2(a, c)
    }
  }

  test("😀 pythest: if x,y,z is a pythagorean then 2y,2x,2z is a pythagorean") {
    forAll { (x: Int, y: Int, z: Int) =>
      UtilFunctions.pythTest(x, y, z) == true shouldBe
        UtilFunctions.pythTest(2 * y, 2 * x, 2 * z) == true
    }
  }

  test(
    "😄 y,x,z should be a pythagorean triple if x,y,z is a pythagorean tripple"
  ) {
    forAll(triplesGen) { a: (Int, Int, Int) =>
      UtilFunctions.pythTest(a._1, a._2, a._3) shouldBe
        UtilFunctions.pythTest(a._2, a._1, a._3)
    }
  }
}
