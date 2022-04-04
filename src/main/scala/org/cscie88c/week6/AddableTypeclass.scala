package org.cscie88c.week6

// Write a generic trait AddableTypeclass parameterized by type A
trait AddableTypeclass[A] {
  // add trait methods below
  def addTwoValues[B](a: A, b: B): A
}

object AddableTypeclass {

  implicit val intAddableTypeclass: AddableTypeclass[Int] =
    new AddableTypeclass[Int] {
      def addTwoValues[B](a: Int, b: B): Int =
        if (b.isInstanceOf[Int]) a + b.asInstanceOf[Int] else a
    }

  implicit val boolAddableTypeclass: AddableTypeclass[Boolean] =
    new AddableTypeclass[Boolean] {
      def addTwoValues[B](a: Boolean, b: B): Boolean =
        if (b.isInstanceOf[Boolean]) a || b.asInstanceOf[Boolean] else a
    }

  implicit val employeeAddableTypeclass: AddableTypeclass[Employee] =
    new AddableTypeclass[Employee] {
      def addTwoValues[B](a: Employee, b: B): Employee = if (
        b.isInstanceOf[Employee]
      )
        Employee(
          s"${a.name} & ${b.asInstanceOf[Employee].name}",
          a.age + b.asInstanceOf[Employee].age,
          a.salary + b.asInstanceOf[Employee].salary
        )
      else a
    }

}

object AddableAggregator {
  def sumWithAddable[A](
      list: List[A]
    )(implicit
      addable: AddableTypeclass[A]
    ): A = list.reduce(addable.addTwoValues(_, _))
}
