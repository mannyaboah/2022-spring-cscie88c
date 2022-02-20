package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class StudentTest extends StandardTest {
  "Student Management System" when {
    "creating a student" should {
      val testStudentI =
        Student("T'Challa", "kingT'challa@wakanda.gov", "Marshall Arts", 100)
      val testStudentII = Student("Shuri", "princess.gov", "Science", 100)

      "have properties - name, email, subject and score 😁" in {
        (!testStudentI.name.isBlank() && !testStudentI
          .email
          .isBlank() && !testStudentI
          .subject
          .isBlank() && testStudentI.score >= 0) shouldBe true
      }

      "have a valid email 😁" in {
        Student.validateEmail(testStudentI) shouldBe true
      }

      "have an invalid email 😟" in {
        Student.validateEmail(testStudentII) shouldBe false
      }
    }

    "creating a list of student" should {

      val students: List[Student] = List(
        Student("Wanda", "wanda@email", "chem", 99),
        Student("parker", "peter@email", "sociology", 100),
        Student("Stark", "tony@email", "science", 100),
        Student("Banner", "banner@email", "science", 100),
        Student("parker", "peter@email", "chem", 92)
      )

      "calculate average by subject 😁" in {
        Student.averageScoreBySubject("science", students) shouldBe 100.0
        Student.averageScoreBySubject("chem", students) shouldBe 95.0
      }

      "calculate average by student 😁" in {
        val peterParker = Student("parker", "", "", 0)
        Student.averageScoreByStudent(peterParker, students) shouldBe 96
      }
    }
  }
}
