package org.cscie88c.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class StudentPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val studentGen: Gen[Student] = for {
    name <- Gen.oneOf("Danny", "Joe", "Bobby", "Susan", "Cindy")
    email <- Gen.oneOf(
      "danny@email.com",
      "joe@email.com",
      "susan@email.com",
      "cindy@email.com"
    )
    subject <- Gen.oneOf("Math", "Art", "Music", "Engineering", "Physics")
    score <- Gen.choose(80, 100)
  } yield Student(name, email, subject, score)

  val studentListGen: Gen[List[Student]] = Gen.listOf(studentGen)

  // complete the student list generator below if attempting bonus problem
  test("😀 verify average score by subject < 100 for math") {
    forAll(studentListGen) { (sGen: List[Student]) =>
      Student.averageScoreBySubject("Math", sGen) < 100.0
    }
  }

  // val studentListGen: Gen[List[Student]] = ???
  test("😀 description contains name and email") {
    forAll(studentGen) { (s: Student) =>
      assert(s.description.contains(s.email))
    }
  }
}
