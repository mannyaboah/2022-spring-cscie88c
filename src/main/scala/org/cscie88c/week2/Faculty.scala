package org.cscie88c.week2

class Faculty(
    cname: String,
    cemail: String,
    ccourseId: String
  ) extends UniversityEmployee(cname, cemail) {
  var courseId: String = ccourseId

  override def description: String =
    s"Name: ${name}, Email: ${email}, Course: ${courseId}"
}
