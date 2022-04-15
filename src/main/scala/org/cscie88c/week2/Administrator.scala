package org.cscie88c.week2

class Administrator(
    name: String,
    email: String,
    cbudget: Long
  ) extends UniversityEmployee(name, email) {
  var budget: Long = cbudget

  override def description: String =
    s"Name: ${name}, Email: ${email}, Budget: $$${budget}"
}
