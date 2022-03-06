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

    val scoreList = studentList.filter(_.subject == subject).map(_.score)
    if (scoreList.isEmpty) 0.0
    else scoreList.reduce(_ + _) / scoreList.size.toDouble
  }

  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
    ): Double = {

    val scoreList = studentList.filter(_.name == student.name).map(_.score)

    if (scoreList.isEmpty) 0.00
    else scoreList.reduce(_ + _) / scoreList.size.toDouble
  }
}
