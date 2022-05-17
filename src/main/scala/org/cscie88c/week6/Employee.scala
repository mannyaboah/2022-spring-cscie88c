package org.cscie88c.week6

final case class Employee(
    name: String,
    age: Int,
    salary: Int
  )

object Employee {

  implicit val employeeOrdering: Ordering[Employee] =
    Ordering.by[Employee, (String, Int)](employee =>
      (employee.name, employee.salary)
    )

  def defaultSortEmployees(employees: List[Employee]): List[Employee] =
    employees.sorted

  def sortEmployeesBySalary(employees: List[Employee]): List[Employee] =
    employees.sortBy(_.salary)

}
