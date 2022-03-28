package org.cscie88c.week2

// complete the definition of the Subject case class and companion object
//-- Subject
final case class Subject(
    id: Int,
    name: String,
    isStem: Boolean
  )
object Subject {

  val allSubjects: List[Subject] = List(
    "1,Physics,true",
    "2,Chemistry,true",
    "3,Math,true",
    "4,English,false"
  ).map(Subject(_))

  def stemSubjects: List[Subject] =
    allSubjects.filter(_.isStem == true)

  def apply(csvInsert: String): Subject = {
    val attribute = csvInsert.split(",")
    new Subject(
      id = attribute(0).toInt,
      name = attribute(1),
      isStem = attribute(2).toBoolean
    )
  }
}
