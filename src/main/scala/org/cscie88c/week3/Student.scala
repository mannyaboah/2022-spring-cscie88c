package org.cscie88c.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
  ) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  def validateEmail(student: Student): Boolean = student.email.contains('@')

  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
    ): Double = {
    var sum = 0
    var count = 0

    for (s <- studentList if s.subject == subject) {
      sum += s.score
      count += 1
    }
    (sum / count).toDouble
  }

  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
    ): Double = {
    var sum = 0
    var count = 0

    for (s <- studentList if s.name == student.name) {
      sum += s.score
      count += 1
    }
    (sum / count).toDouble
  }
}
