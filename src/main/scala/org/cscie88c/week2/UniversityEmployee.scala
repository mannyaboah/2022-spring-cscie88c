package org.cscie88c.week2

class UniversityEmployee(cname: String, cemail: String) {
  var name: String = cname
  var email: String = cemail

  def description: String =
    s"Name: ${name}, Email: ${email}"
}
